package cn.ahzoo.z2blog.controller;

import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.vo.ColumnInfoVO;
import cn.ahzoo.z2blog.model.vo.ColumnItemVO;
import cn.ahzoo.z2blog.service.ColumnService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 专栏视图
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Tag(name = "专栏视图")
@RestController
@RequestMapping("v1/columns")
@AllArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    @Operation(summary = "获取专栏列表")
    @GetMapping("/{categoryId}")
    public ResultList<List<ColumnItemVO>> list(@PathVariable long categoryId,
                                               @RequestParam(value = "p") @Min(value = 1, message = "页码不能小于1") int pagination) {
        return columnService.listColumnByCategoryId(categoryId, pagination);
    }

    @Operation(summary = "获取专栏页信息")
    @GetMapping("/info/{name}")
    public Result<ColumnInfoVO> info(@PathVariable String name) {
        return columnService.getColumnInfoByName(name);
    }

    @Operation(summary = "获取文章页专栏列表")
    @GetMapping("/articles/{articleId}")
    public ResultList<List<ColumnItemVO>> listColumn(@PathVariable long articleId) {
        return columnService.listColumnByArticleId(articleId);
    }
}
