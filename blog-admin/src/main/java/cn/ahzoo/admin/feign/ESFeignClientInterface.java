package cn.ahzoo.admin.feign;

import cn.ahzoo.admin.model.record.ArticleESRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ahzoo-search", path = "v1/es")
public interface ESFeignClientInterface {

    @GetMapping("/")
    List<Long> search(@RequestParam String keyword, @RequestParam int pagination);

    @PostMapping("/")
    ArticleESRecord save(@RequestBody ArticleESRecord articleES);

    @GetMapping("/title")
    List<Long> searchByTitle(@RequestParam String keyword, @RequestParam int pagination);

    @GetMapping("/content")
    List<Long> searchByContent(@RequestParam String keyword, @RequestParam int pagination);

    @GetMapping("/article-id")
    ArticleESRecord searchByArticleId(@RequestParam Long articleId);
}
