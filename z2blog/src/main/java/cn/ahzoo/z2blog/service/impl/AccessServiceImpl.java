package cn.ahzoo.z2blog.service.impl;

import cn.ahzoo.common.constant.RedisConstant;
import cn.ahzoo.utils.utils.DateUtil;
import cn.ahzoo.z2blog.constant.Constant;
import cn.ahzoo.z2blog.exception.BizException;
import cn.ahzoo.z2blog.mapper.AccessMapper;
import cn.ahzoo.z2blog.model.entity.Access;
import cn.ahzoo.z2blog.service.AccessService;
import cn.ahzoo.common.utils.IpUtil;
import cn.ahzoo.z2blog.utils.RedisUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccessServiceImpl extends ServiceImpl<AccessMapper, Access>
        implements AccessService {

    private static final Logger logger = LoggerFactory.getLogger(AccessServiceImpl.class);

    private final RedisUtil redisUtil;
    private final HttpServletRequest request;

    @Override
    public void cacheWebsitePVAndUV(String ipAddress) {
        try {
            int day = DateUtil.getDayOfToday();
            String key = RedisConstant.ACCESS_PREFIX + day + RedisConstant.KEY_SEPARATOR + "website";
            redisUtil.incrBy(key + "_pv", 1);
            redisUtil.sAdd(key + "_ip", ipAddress);
            setKeyExpire(key + "_pv");
            setKeyExpire(key + "_ip");
        } catch (Exception e) {
            logger.error("访问量缓存失败：{}", e.getMessage());
        }
    }

    @Override
    public void cacheArticlePVAndUV(Long articleId) {
        try {
            String ipAddress = IpUtil.getIpAddress(request);
            int day = DateUtil.getDayOfToday();
            String key = RedisConstant.ACCESS_PREFIX + day + RedisConstant.KEY_SEPARATOR + "article";
            redisUtil.hIncrBy(key + "_pv", String.valueOf(articleId), 1);
            Long count = redisUtil.sAdd(key + "_ip_" + articleId, ipAddress);
            setKeyExpire(key + "_pv");
            setKeyExpire(key + "_ip_" + articleId);
            // count为0时表示set集合已存在
            if (count > 0) {
                redisUtil.hIncrBy(key + "_uv", String.valueOf(articleId), 1);
                setKeyExpire(key + "_uv");
            }
        } catch (Exception e) {
            logger.error("文章访问量缓存失败：{}", e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstant.SYSTEM_STATISTICS_KEY, allEntries = true)
    @Override
    public void updateWebSiteAccess() {
        int yesterday = DateUtil.getDayOfYesterday();
        logger.info("开始更新全站访问量，日期：{}", yesterday);
        saveWebsiteAccess(yesterday);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateArticleAccess() {
        int yesterday = DateUtil.getDayOfYesterday();
        logger.info("开始更新文章访问量，日期：{}", yesterday);
        saveArticleAccess(yesterday);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveWebsiteAccess(int day) {
        int uv = 0;
        int pv = 0;
        try {
            String webKey = RedisConstant.ACCESS_PREFIX + day + RedisConstant.KEY_SEPARATOR + "website";
            String pvStr = redisUtil.get(webKey + "_pv");
            if (StringUtils.isNotEmpty(pvStr)) {
                pv = Integer.parseInt(pvStr);
            }
            uv = redisUtil.sSize(webKey + "_ip").intValue();
            Access dbWebAccess = getById(Constant.DEFAULT_LONG);
            if (ObjectUtils.isNotEmpty(dbWebAccess)) {
                uv += dbWebAccess.getUv();
                pv += dbWebAccess.getPv();
            }
            Access webAccess = new Access(Constant.DEFAULT_LONG, uv, pv, Constant.TYPE_WEBSITE);
            saveOrUpdate(webAccess);
        } catch (Exception e) {
            logger.error("更新全站访问量失败，message:{}，UV: {}，PV：{}", e.getMessage(), uv, pv);
            throw new BizException(e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveArticleAccess(int day) {
        int uv = 0;
        int pv = 0;
        List<Access> accessResult = new ArrayList<>();
        try {
            String articleKey = RedisConstant.ACCESS_PREFIX + day + RedisConstant.KEY_SEPARATOR + "article";
            Map<Object, Object> articleUVAccessMap = redisUtil.hGetAll(articleKey + "_uv");
            Map<Object, Object> articlePVAccessMap = redisUtil.hGetAll(articleKey + "_pv");
            List<Access> accessList = buildAccessList(articleUVAccessMap, articlePVAccessMap);
            if (accessList.isEmpty()) {
                logger.error("文章访问量列表为空：{}", accessList);
                return;
            }
            accessResult = sumPvAndUv(accessList);
            saveOrUpdateBatch(accessResult);
        } catch (Exception e) {
            logger.error("更新文章访问量失败，message:{}，UV: {}，PV：{}, accessResult:{}", e.getMessage(), uv, pv, accessResult);
            throw new BizException(e.getMessage());
        }
    }

    private List<Access> sumPvAndUv(List<Access> accessList) {
        List<Long> ids = accessList.stream().map(Access::getArticleId).collect(Collectors.toList());
        List<Access> dbAccesseList = baseMapper.selectBatchIds(ids);
        Map<Long, Access> accessMap = new HashMap<>();

        accessList.forEach(access -> accessMap.put(access.getArticleId(), access));

        dbAccesseList.forEach(dbAccess -> {
            Access access = accessMap.get(dbAccess.getArticleId());
            if (access != null) {
                Integer dbUv = Optional.ofNullable(dbAccess.getUv()).orElse(0);
                Integer dbPv = Optional.ofNullable(dbAccess.getPv()).orElse(0);
                Integer uv = Optional.ofNullable(access.getUv()).orElse(0);
                Integer pv = Optional.ofNullable(access.getPv()).orElse(0);
                access.setUv(uv + dbUv);
                access.setPv(pv + dbPv);
            } else {
                accessMap.put(dbAccess.getArticleId(), dbAccess);
            }
        });
        return new ArrayList<>(accessMap.values());
    }

    private ArrayList<Access> buildAccessList(Map<Object, Object> articleUVAccessMap, Map<Object, Object> articlePVAccessMap) {
        ArrayList<Access> accessList = new ArrayList<>();
        articleUVAccessMap.forEach((articleIdObj, uvObj) -> {
            String articleIdStr = String.valueOf(articleIdObj);
            Integer pv = Integer.parseInt(String.valueOf(articlePVAccessMap.get(articleIdStr)));
            Integer uv = Integer.parseInt(String.valueOf(uvObj));
            Access access = new Access(Long.parseLong(articleIdStr), uv, pv, Constant.TYPE_ARTICLE);
            accessList.add(access);
        });
        return accessList;
    }

    private void setKeyExpire(String key) {
        Long expire = redisUtil.getExpire(key);
        // 小于0表示永不过期
        if (expire < 0) {
            redisUtil.expire(key, RedisConstant.DEFAULT_KEY_EXPIRE_HOUR, TimeUnit.HOURS);
        }
    }
}
