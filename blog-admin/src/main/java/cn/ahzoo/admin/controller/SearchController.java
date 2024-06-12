package cn.ahzoo.admin.controller;

import cn.ahzoo.admin.model.vo.ArticleVO;
import cn.ahzoo.admin.service.SearchService;
import cn.ahzoo.utils.model.ResultList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 搜索功能
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@RestController
@RequestMapping("v1/a/search")
@AllArgsConstructor
@Tag(name = "搜索视图")
public class SearchController {

    private final SearchService searchService;


    @Operation(summary = "通过关键词搜索文章")
    @GetMapping("/keyword")
    public ResultList<List<ArticleVO>> list(@RequestParam(value = "k") String keyword,
                                            @RequestParam(value = "p") int pagination) {
        return searchService.searchAll(keyword, pagination);
    }
}
