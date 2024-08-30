package cn.ahzoo.z2blog.service.impl;

import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import cn.ahzoo.z2blog.feign.ESFeignClientInterface;
import cn.ahzoo.z2blog.mapper.ArticleMapper;
import cn.ahzoo.z2blog.model.entity.Article;
import cn.ahzoo.z2blog.model.mapstruct.ArticleMapping;
import cn.ahzoo.z2blog.model.vo.ArticleVO;
import cn.ahzoo.z2blog.service.SearchService;
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
        return getResultList(pagination, ids);
    }

    @Override
    public ResultList<List<ArticleVO>> searchByTitle(String title, int pagination) {
        List<Long> ids = esFeignClientInterface.searchByTitle(title, pagination);
        return getResultList(pagination, ids);
    }

    @Override
    public ResultList<List<ArticleVO>> searchByContent(String content, int pagination) {
        List<Long> ids = esFeignClientInterface.searchByContent(content, pagination);
        return getResultList(pagination, ids);
    }

    private ResultList<List<ArticleVO>> getResultList(int pagination, List<Long> ids) {
        if(ids.isEmpty()){
            return ResultList.success(ResultPage.emptyPage(), null);
        }
        List<ArticleVO> articleVOList = articleMapper.selectBatchByIds(ids);
        return ResultList.success(ResultPage.defaultPage(articleVOList.size(), pagination), articleVOList);
    }

}
