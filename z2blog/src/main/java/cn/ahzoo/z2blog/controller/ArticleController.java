package cn.ahzoo.z2blog.controller;

import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.vo.ArticleItemVO;
import cn.ahzoo.z2blog.model.vo.ArticleVO;
import cn.ahzoo.z2blog.service.ArticleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 文章视图
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Tag(name = "文章视图")
@RestController
@RequestMapping("v1/articles")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "获取首页文章列表")
    @GetMapping("")
    public ResultList<List<ArticleItemVO>> list(@RequestParam(value = "p", defaultValue = "1") @Min(value = 1, message = "页码不能小于1") int pagination) {
        return articleService.listArticle(pagination);
    }

    @Operation(summary = "获取文章详情")
    @GetMapping("/{articlePath}")
    public Result<ArticleVO> detail(@PathVariable String articlePath) {
        return articleService.getArticleDetail(articlePath);
    }

    @Operation(summary = "获取专栏页文章列表")
    @GetMapping("/columns/{columnId}")
    public ResultList<List<ArticleItemVO>> listArticle(@PathVariable long columnId,
                                                       @RequestParam(value = "p") @Min(value = 1, message = "页码不能小于1") int pagination) {
        return articleService.listArticleByColumnId(columnId, pagination);
    }
}
