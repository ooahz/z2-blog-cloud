package cn.ahzoo.search.service;

import cn.ahzoo.search.model.entity.Article;

import java.util.List;

public interface ArticleService {

    List<Long> searchByTitle(String title, int pagination);

    List<Long> searchByContent(String content, int pagination);

    Article searchByArticleId(Long articleId);

    List<Long> fullSearch(String keyword, int pagination);

    void save(Article article);

    void update(Article article);
}
