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

    @Override
    public void cacheWebsitePVAndUV(String ipAddress) {
        try {
            int day = DateUtil.getDayOfToday();
            String key = RedisConstant.ACCESS_PREFIX + day + RedisConstant.KEY_SEPARATOR + "website";
            redisUtil.incrBy(key + "_pv", 1);
            redisUtil.hllAdd(key + "_uv", ipAddress);
            setKeyExpire(key + "_pv");
            setKeyExpire(key + "_uv");
        } catch (Exception e) {
            logger.error("访问量缓存失败：{}", e.getMessage());
        }
    }

    @Override
    public void cacheArticleVisit(Long articleId) {
        try {
            int day = DateUtil.getDayOfToday();
            String key = RedisConstant.ACCESS_PREFIX + day + RedisConstant.KEY_SEPARATOR + "article";
            redisUtil.hIncrBy(key + "_pv", String.valueOf(articleId), 1);
            setKeyExpire(key + "_pv");
        } catch (Exception e) {
            logger.error("文章访问量缓存失败：{}", e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstant.SYSTEM_STATISTICS_KEY, allEntries = true)
    @Override
    public void updateWebSiteAccess() {
        int yesterday = DateUtil.getDayOfYesterday();
        String yesterdayStr = DateUtil.getYesterdayStr(Constant.ACCESS_DATEFORMAT);
        logger.info("开始更新全站访问量，日期：{}", yesterday);
        long uv = 0;
        long pv = 0;
        try {
            String webKey = RedisConstant.ACCESS_PREFIX + yesterday + RedisConstant.KEY_SEPARATOR + "website";
            String pvStr = redisUtil.get(webKey + "_pv");
            if (StringUtils.isNotEmpty(pvStr)) {
                pv = Integer.parseInt(pvStr);
            }
            uv = redisUtil.hllSize(webKey + "_uv");
            List<Access> accessList = list();
            Access webAccess = new Access();
            if (accessList.size() > 0) {
                Access dbAccess = accessList.getFirst();
                uv += dbAccess.getUv();
                pv += dbAccess.getPv();
                webAccess.setId(dbAccess.getId());
            }
            webAccess.setUv(uv);
            webAccess.setPv(pv);
            webAccess.setDate(yesterdayStr);
            saveOrUpdate(webAccess);
        } catch (Exception e) {
            logger.error("更新全站访问量失败，message:{}，UV: {}，PV：{}", e.getMessage(), uv, pv);
            throw new BizException(e.getMessage());
        }
    }

    private void setKeyExpire(String key) {
        Long expire = redisUtil.getExpire(key);
        // 小于0表示永不过期
        if (expire < 0) {
            redisUtil.expire(key, RedisConstant.DEFAULT_KEY_EXPIRE_HOUR, TimeUnit.HOURS);
        }
    }
}
