package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.feign.ESFeignClientInterface;
import cn.ahzoo.admin.mapper.ArticleMapper;
import cn.ahzoo.admin.model.entity.Article;
import cn.ahzoo.admin.model.mapstruct.ArticleMapping;
import cn.ahzoo.admin.model.vo.ArticleVO;
import cn.ahzoo.admin.service.SearchService;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final ESFeignClientInterface esFeignClientInterface;
    private final ArticleMapper articleMapper;

    @Override
    public ResultList<List<ArticleVO>> searchAll(String keyword, int pagination) {
        List<Long> ids = esFeignClientInterface.search(keyword, pagination);
        if (ids.isEmpty()) {
            return ResultList.success(ResultPage.defaultPage(0, pagination), List.of());
        }
        List<Article> articles = articleMapper.selectBatchIds(ids);
        List<ArticleVO> articleVOS = ArticleMapping.INSTANCE.articleList2VOs(articles);
        return ResultList.success(ResultPage.defaultPage(articleVOS.size(), pagination), articleVOS);
    }
}
