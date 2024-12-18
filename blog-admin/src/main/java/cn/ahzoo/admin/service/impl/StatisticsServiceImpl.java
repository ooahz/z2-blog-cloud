package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.mapper.ArticleMapper;
import cn.ahzoo.admin.mapper.ColumnMapper;
import cn.ahzoo.admin.model.dto.WebsiteAccessDTO;
import cn.ahzoo.admin.model.vo.ArticleStatisticsVO;
import cn.ahzoo.admin.model.vo.StatisticsVO;
import cn.ahzoo.admin.model.vo.ViewStatisticsVO;
import cn.ahzoo.admin.service.AccessService;
import cn.ahzoo.admin.service.StatisticsService;
import cn.ahzoo.admin.utils.RedisUtil;
import cn.ahzoo.common.constant.RedisConstant;
import cn.ahzoo.utils.utils.DateUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private static final Logger logger = LoggerFactory.getLogger(StatisticsServiceImpl.class);

    private final AccessService accessService;
    private final ArticleMapper articleMapper;
    private final ColumnMapper columnMapper;
    private final RedisUtil redisUtil;

    @Cacheable(RedisConstant.SYSTEM_STATISTICS_KEY)
    @Override
    public StatisticsVO homeStatistics() {
        return homeStatisticsByDB();
    }

    private StatisticsVO homeStatisticsByDB() {
        logger.info("= =+从数据库获取统计数据");
        WebsiteAccessDTO websiteAccess = accessService.getWebsiteAccess();
        ViewStatisticsVO websiteViewStatisticsVO = new ViewStatisticsVO(websiteAccess.getPv(), websiteAccess.getUv());
        ArticleStatisticsVO articleStatistics = articleMapper.selectArticleStatistics();
        Long count = columnMapper.selectCount(null);
        return StatisticsVO.builder()
                .yesterdayViews(websiteViewStatisticsVO)
                .articles(articleStatistics)
                .columnsTotal(count)
                .build();
    }

    @Override
    public ViewStatisticsVO getRedisWebsiteAccess() {
        long pv = 0;
        long uv = 0;
        try {
            int day = DateUtil.getDayOfToday();
            String webKey = RedisConstant.ACCESS_PREFIX + day + RedisConstant.KEY_SEPARATOR + "website";
            String pvStr = redisUtil.get(webKey + "_pv");
            if (StringUtils.isNotEmpty(pvStr)) {
                pv = Integer.parseInt(pvStr);
            }
            uv = redisUtil.hllSize(webKey + "_uv");
        } catch (Exception e) {
            logger.error("获取全站访问量失败：{}", e.getMessage());
        }
        return new ViewStatisticsVO(pv, uv);
    }
}
