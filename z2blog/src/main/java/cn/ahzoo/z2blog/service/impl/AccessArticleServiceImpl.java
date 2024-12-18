package cn.ahzoo.z2blog.service.impl;

import cn.ahzoo.common.constant.RedisConstant;
import cn.ahzoo.utils.utils.DateUtil;
import cn.ahzoo.z2blog.constant.Constant;
import cn.ahzoo.z2blog.exception.BizException;
import cn.ahzoo.z2blog.mapper.AccessArticleMapper;
import cn.ahzoo.z2blog.model.entity.AccessArticle;
import cn.ahzoo.z2blog.service.AccessArticleService;
import cn.ahzoo.z2blog.utils.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccessArticleServiceImpl extends ServiceImpl<AccessArticleMapper, AccessArticle>
        implements AccessArticleService {

    private static final Logger logger = LoggerFactory.getLogger(AccessArticleServiceImpl.class);

    private final RedisUtil redisUtil;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateArticleAccess() {
        int yesterday = DateUtil.getDayOfYesterday();
        logger.info("开始更新文章访问量，日期：{}", yesterday);
        int uv = 0;
        List<AccessArticle> accessResult = new ArrayList<>();
        try {
            String articleKey = RedisConstant.ACCESS_PREFIX + yesterday + RedisConstant.KEY_SEPARATOR + "article";
            Map<Object, Object> articleUVAccessMap = redisUtil.hGetAll(articleKey + "_pv");
            List<AccessArticle> accessList = buildAccessList(articleUVAccessMap);
            if (accessList.isEmpty()) {
                logger.error("文章访问量列表为空：{}", accessList);
                return;
            }
            accessResult = sumVisit(accessList);
            saveOrUpdateBatch(accessResult);
        } catch (Exception e) {
            logger.error("更新文章访问量失败，message:{}，PV: {}, accessResult:{}", e.getMessage(), uv, accessResult);
            throw new BizException(e.getMessage());
        }
    }

    private List<AccessArticle> sumVisit(List<AccessArticle> accessList) {
        List<Long> ids = accessList.stream().map(AccessArticle::getArticleId).collect(Collectors.toList());
        List<AccessArticle> dbAccesseList = baseMapper.selectBatchByArticleIds(ids);
        Map<Long, AccessArticle> accessMap = new HashMap<>();

        accessList.forEach(access -> accessMap.put(access.getArticleId(), access));

        dbAccesseList.forEach(dbAccess -> {
            AccessArticle access = accessMap.get(dbAccess.getArticleId());
            if (access != null) {
                Long dbPv = Optional.ofNullable(dbAccess.getPv()).orElse(0L);
                Long pv = Optional.ofNullable(access.getPv()).orElse(0L);
                access.setPv(pv + dbPv);
                access.setId(dbAccess.getId());
            } else {
                accessMap.put(dbAccess.getArticleId(), dbAccess);
            }
        });
        return new ArrayList<>(accessMap.values());
    }

    private ArrayList<AccessArticle> buildAccessList(Map<Object, Object> articlePVAccessMap) {
        String yesterdayStr = DateUtil.getYesterdayStr(Constant.ACCESS_DATEFORMAT);
        ArrayList<AccessArticle> accessList = new ArrayList<>();
        articlePVAccessMap.forEach((articleIdObj, pvObj) -> {
            String articleIdStr = String.valueOf(articleIdObj);
            Long pv = Long.parseLong(String.valueOf(pvObj));
            AccessArticle access = new AccessArticle(Long.parseLong(articleIdStr), pv, yesterdayStr);
            accessList.add(access);
        });
        return accessList;
    }
}
