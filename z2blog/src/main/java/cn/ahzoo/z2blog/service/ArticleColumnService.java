package cn.ahzoo.z2blog.service;

import cn.ahzoo.z2blog.model.vo.ArticleItemVO;

import java.util.List;

public interface ArticleColumnService {

    /**
     * 获取专栏界面下的文章列表
     */
    List<ArticleItemVO> listArticleByColumnId(long columnId, int pagination, String sortBy);

    List<ArticleItemVO> listArticleByColumnId(long columnId, int pagination, String sortBy, int pageSize);

    /**
     * 获取专栏界面下的文章数量
     */
    Long countArticleByColumnId(long columnId);
}
