package cn.ahzoo.z2blog.feign;

import cn.ahzoo.z2blog.model.record.ArticleESRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ahzoo-search", path = "v1/es")
public interface ESFeignClientInterface {

    @GetMapping("/")
    List<Long> search(@RequestParam String keyword, @RequestParam int pagination);

    @GetMapping("/title")
    List<Long> searchByTitle(@RequestParam String keyword, @RequestParam int pagination);

    @GetMapping("/content")
    List<Long> searchByContent(@RequestParam String keyword, @RequestParam int pagination);

    @GetMapping("/article-id")
    ArticleESRecord searchByArticleId(@RequestParam Long articleId);
}
