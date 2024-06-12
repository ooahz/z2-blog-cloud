package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.entity.Article;
import cn.ahzoo.admin.model.vo.ArticleItemVO;
import cn.ahzoo.admin.model.vo.ArticleVO;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ArticleService extends IService<Article> {

    Result<ArticleVO> getArticleDetail(Long articleId);

    ResultList<List<ArticleItemVO>> listArticle(int pagination, String status, String type, String columnId);

    Result<?> saveArticle(ArticleVO articleVO);

    Result<?> updateArticle(ArticleVO articleVO);

    Result<?> removeArticleById(Long id);

    /**
     * 更新文章的部分字段
     */
    Result<?> updateArticlePart(ArticleVO articleVO);
}
