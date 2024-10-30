package cn.ahzoo.admin.controller;

import cn.ahzoo.admin.annotation.SystemLogger;
import cn.ahzoo.admin.model.vo.ArticleItemVO;
import cn.ahzoo.admin.model.vo.ArticleVO;
import cn.ahzoo.admin.service.ArticleService;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 文章相关
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Tag(name = "文章视图")
@RestController
@RequestMapping("v1/a/articles")
@AllArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary = "获取文章详情")
    @GetMapping("/{articleId}")
    public Result<ArticleVO> detail(@PathVariable long articleId) {
        return articleService.getArticleDetail(articleId);
    }

    @Operation(summary = "获取文章列表")
    @GetMapping("")
    public ResultList<List<ArticleItemVO>> list(@RequestParam(value = "p") int pagination,
                                                @RequestParam(required = false) String status,
                                                @RequestParam(required = false) String type,
                                                @RequestParam(required = false) String columnId) {
        return articleService.listArticle(pagination, status, type, columnId);
    }

    @Operation(summary = "新增文章")
    @SaCheckRole("admin")
    @PostMapping("")
    @SystemLogger(value = "新增文章", param = "article")
    public Result<?> save(@Validated @RequestBody ArticleVO articleVO) {
        return articleService.saveArticle(articleVO);
    }

    @Operation(summary = "更新文章")
    @SaCheckRole("admin")
    @PutMapping("")
    @SystemLogger(value = "更新文章", param = "article")
    public Result<?> update(@Validated @RequestBody ArticleVO articleVO) {
        return articleService.updateArticle(articleVO);
    }

    @Operation(summary = "更新文章部分参数")
    @SaCheckRole("admin")
    @PostMapping("/part")
    @SystemLogger(value = "更新文章", param = "false")
    public Result<?> updatePart(@RequestBody ArticleVO articleVO) {
        return articleService.updateArticlePart(articleVO);
    }

    @Operation(summary = "删除文章")
    @SaCheckRole("admin")
    @DeleteMapping("/{articleId}")
    @SystemLogger(value = "删除文章")
    public Result<?> remove(@PathVariable Long articleId) {
        return articleService.removeArticleById(articleId);
    }
}
