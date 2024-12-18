package cn.ahzoo.search.controller;

import cn.ahzoo.search.service.SearchService;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.vo.ArticleItemVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
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
 * @date 2024/12
 */
@Tag(name = "搜索视图")
@RestController
@RequestMapping("v1/search")
@AllArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @Operation(summary = "通过关键词搜索·")
    @GetMapping("")
    public ResultList<List<ArticleItemVO>> search(@RequestParam(value = "k") String keyword,
                                                  @RequestParam(value = "p", defaultValue = "1") @Min(value = 1, message = "页码不能小于1") int pagination) {
        return searchService.searchArticle(keyword, pagination);
    }
}
