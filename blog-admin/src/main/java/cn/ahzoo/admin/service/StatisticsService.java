package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.vo.StatisticsVO;
import cn.ahzoo.admin.model.vo.ViewStatisticsVO;

public interface StatisticsService {

    /**
     * 首页统计数据
     */
    StatisticsVO homeStatistics();

    /**
     * 获取实时缓存的访问量数据
     */
    ViewStatisticsVO getRedisWebsiteAccess();
}
