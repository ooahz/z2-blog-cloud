package cn.ahzoo.search.service.impl;

import cn.ahzoo.search.constants.PageConstants;
import cn.ahzoo.search.model.entity.Article;
import cn.ahzoo.search.repository.ArticleRepository;
import cn.ahzoo.search.service.ArticleService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Override
    public List<Long> searchByTitle(String title, int pagination) {
        int paginationIndex = pagination - 1;
        Pageable pageable = PageRequest.of(paginationIndex, PageConstants.DEFAULT_PAGE_SIZE);
        List<Article> articleList = articleRepository.findArticleByTitleContains(title, pageable);
        return articleList.stream().map(Article::getArticleId).toList();

    }

    @Override
    public List<Long> searchByContent(String content, int pagination) {
        int paginationIndex = pagination - 1;
        Pageable pageable = PageRequest.of(paginationIndex, PageConstants.DEFAULT_PAGE_SIZE);
        List<Article> articleList = articleRepository.findArticleByContentLike(content, pageable);
        return articleList.stream().map(Article::getArticleId).toList();
    }

    @Override
    public Article searchByArticleId(Long articleId) {
        return articleRepository.findArticleByArticleId(articleId);
    }

    @Override
    public List<Long> fullSearch(String keyword, int pagination) {
        int paginationIndex = pagination - 1;
        Pageable pageable = PageRequest.of(paginationIndex, PageConstants.DEFAULT_PAGE_SIZE);
        List<Article> articeleList = articleRepository.findArticleByTitleContainsOrContentLike(keyword, keyword, pageable);
        return articeleList.stream().map(Article::getArticleId).toList();
    }

    @Override
    public void save(Article article) {
        articleRepository.save(article);
    }

    @Override
    public void update(Article article) {
        Article oldArticle = searchByArticleId(article.getArticleId());
        if (ObjectUtils.isNotEmpty(oldArticle)) {
            article.setId(oldArticle.getId());
        }
        articleRepository.save(article);
    }
}
