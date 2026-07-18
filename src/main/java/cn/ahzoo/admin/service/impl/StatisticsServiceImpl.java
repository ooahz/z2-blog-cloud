package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.mapper.SysArticleMapper;
import cn.ahzoo.admin.mapper.SysColumnMapper;
import cn.ahzoo.admin.mapper.SysFriendMapper;
import cn.ahzoo.admin.model.vo.ArticleStatisticsVO;
import cn.ahzoo.admin.model.vo.StatisticsVO;
import cn.ahzoo.admin.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final SysArticleMapper articleMapper;
    private final SysColumnMapper columnMapper;
    private final SysFriendMapper friendMapper;

    @Override
    public StatisticsVO getStatistics() {
        ArticleStatisticsVO articleStatistics = new ArticleStatisticsVO();
        articleStatistics.setTotal(articleMapper.countArticle());
        articleStatistics.setPublish(articleMapper.countArticleByStatus(1));

        StatisticsVO statisticsVO = new StatisticsVO();
        statisticsVO.setArticles(articleStatistics);
        statisticsVO.setColumns(columnMapper.selectCount(null));
        statisticsVO.setFriends(friendMapper.selectCount(null));
        return statisticsVO;
    }
}
