package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.dto.ArticleDTO;
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

    Result<?> saveArticle(ArticleDTO articleDTO);

    Result<?> updateArticle(ArticleDTO articleDTO);

    Result<?> removeArticleById(Long id);

    /**
     * 更新文章的部分字段
     */
    Result<?> updateArticlePart(ArticleDTO articleDTO);
}
