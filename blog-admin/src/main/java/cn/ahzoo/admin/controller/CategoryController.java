package cn.ahzoo.admin.controller;

import cn.ahzoo.admin.annotation.SystemLogger;
import cn.ahzoo.admin.model.dto.CategoryDTO;
import cn.ahzoo.admin.model.vo.CategoryVO;
import cn.ahzoo.admin.service.CategoryService;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 分类相关
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Tag(name = "分类视图")
@RestController
@RequestMapping("v1/a/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "获取分类列表")
    @GetMapping("")
    public ResultList<List<CategoryVO>> list() {
        return categoryService.listCategory();
    }

    @Operation(summary = "保存分类")
    @SaCheckRole("admin")
    @PostMapping("")
    public Result<CategoryVO> save(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.saveCategory(categoryDTO);
    }

    @Operation(summary = "更新分类")
    @SaCheckRole("admin")
    @PutMapping("")
    @SystemLogger(value = "更新分类")
    public Result<CategoryVO> update(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.updateCategory(categoryDTO);
    }

    @Operation(summary = "删除分类")
    @SaCheckRole("admin")
    @DeleteMapping("/{categoryId}")
    @SystemLogger(value = "删除分类")
    public Result<CategoryVO> delete(@PathVariable Long categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}
