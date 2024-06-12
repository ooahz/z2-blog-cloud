package cn.ahzoo.z2blog.controller;

import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.vo.ArticleVO;
import cn.ahzoo.z2blog.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 搜索视图
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Tag(name = "搜索视图")
@RestController
@RequestMapping("v1/search")
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "通过关键词全文搜索")
    @GetMapping("/keyword")
    public ResultList<List<ArticleVO>> listKeyword(@NotBlank(message = "关键词不能为空") @RequestParam String keyword,
                                                   @Min(value = 1, message = "页码不能小于1") @RequestParam int pagination) {
        return searchService.searchAll(keyword, pagination);
    }

    @Operation(summary = "通过标题搜索")
    @GetMapping("/title")
    public ResultList<List<ArticleVO>> listTitle(@NotBlank(message = "关键词不能为空") @RequestParam String keyword,
                                                 @Min(value = 1, message = "页码不能小于1") @RequestParam int pagination) {
        return searchService.searchByTitle(keyword, pagination);
    }

    @Operation(summary = "通过内容搜索")
    @GetMapping("/content")
    public ResultList<List<ArticleVO>> listContent(@NotBlank(message = "关键词不能为空") @RequestParam String keyword,
                                                   @Min(value = 1, message = "页码不能小于1") @RequestParam int pagination) {
        return searchService.searchByContent(keyword, pagination);
    }
}
