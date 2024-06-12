package cn.ahzoo.search.repository;

import cn.ahzoo.search.model.entity.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description 继承ElasticsearchRepository实现简单的增删改查
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/4
 */
@Repository
public interface ArticleRepository extends ElasticsearchRepository<Article, String> {

    List<Article> findArticleByTitleContains(String title, Pageable pageable);

    List<Article> findArticleByContentLike(String content, Pageable pageable);

    List<Article> findArticleByTitleContainsOrContentLike(String title, String content, Pageable pageable);

    Article findArticleByArticleId(Long articleId);
}
