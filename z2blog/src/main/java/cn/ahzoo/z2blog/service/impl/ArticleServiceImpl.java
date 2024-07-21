package cn.ahzoo.z2blog.service.impl;

import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import cn.ahzoo.z2blog.constant.Constant;
import cn.ahzoo.z2blog.mapper.ArticleMapper;
import cn.ahzoo.z2blog.model.dto.ArticleContentDTO;
import cn.ahzoo.z2blog.model.entity.Article;
import cn.ahzoo.z2blog.model.vo.ArticleItemVO;
import cn.ahzoo.z2blog.model.vo.ArticleVO;
import cn.ahzoo.z2blog.service.AccessService;
import cn.ahzoo.z2blog.service.ArticleColumnService;
import cn.ahzoo.z2blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    private final ArticleColumnService articleColumnService;
    private final AccessService accessService;

    @Override
    public ResultList<List<ArticleItemVO>> listArticle(int pagination) {
        int paginationIndex = pagination - 1;
        List<ArticleItemVO> articleItemVOList = baseMapper
                .listArticleItem(paginationIndex * Constant.PAGE_SIZE, Constant.PAGE_SIZE);
        long countArticle = baseMapper.countArticle();
        return ResultList.success(
                new ResultPage(countArticle, articleItemVOList.size(), Constant.PAGE_SIZE, pagination),
                articleItemVOList);
    }

    @Override
    public Result<ArticleVO> getArticleDetail(String articlePath) {
        ArticleVO articleVO = baseMapper.getArticleByPath(articlePath);
        accessService.cacheArticlePVAndUV(articleVO.getId());
        buildArticleContent(articleVO);
        return Result.success(articleVO);
    }

    @Override
    public ResultList<List<ArticleItemVO>> listArticleByColumnId(long columnId, int pagination) {
        List<ArticleItemVO> articleItemList = articleColumnService
                .listArticleByColumnId(columnId, pagination);
        Long count = articleColumnService.countArticleByColumnId(columnId);
        return ResultList.success(
                new ResultPage(count, articleItemList.size(), Constant.PAGE_SIZE, pagination),
                articleItemList);
    }

    private void buildArticleContent(ArticleVO articleVO) {
        buildArticleContentByDB(articleVO);
    }

    private void buildArticleContentByDB(ArticleVO articleVO) {
        ArticleContentDTO articleContentById = baseMapper.getArticleContentById(articleVO.getId());
        articleVO.setContent(articleContentById.getContent());
    }
}




