package cn.ahzoo.search.controller;

import cn.ahzoo.search.model.entity.Article;
import cn.ahzoo.search.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/es")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public List<Long> search(@RequestParam String keyword,
                             @RequestParam int pagination) {
        return articleService.fullSearch(keyword, pagination);
    }

    @GetMapping("/article-id")
    public Article searchByArticleId(@RequestParam Long articleId) {
        return articleService.searchByArticleId(articleId);
    }

    @GetMapping("/title")
    public List<Long> searchByTitle(@RequestParam String keyword,
                                    @RequestParam int pagination) {
        return articleService.searchByTitle(keyword, pagination);
    }

    @GetMapping("/content")
    public List<Long> searchByContent(@RequestParam String keyword,
                                      @RequestParam int pagination) {
        return articleService.searchByContent(keyword, pagination);
    }

    @PostMapping("/")
    public void save(@RequestBody Article article) {
        articleService.save(article);
    }

    @PutMapping("/")
    public void update(@RequestParam Article article) {
        articleService.update(article);
    }
}
