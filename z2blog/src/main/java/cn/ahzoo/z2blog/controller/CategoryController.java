package cn.ahzoo.z2blog.controller;

import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.vo.CategoryColumnVO;
import cn.ahzoo.z2blog.model.vo.CategoryVO;
import cn.ahzoo.z2blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 分类视图
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Tag(name = "分类视图")
@RestController
@RequestMapping("v1/categories")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "获取分类列表")
    @GetMapping("/list")
    public ResultList<List<CategoryVO>> list() {
        return categoryService.listCategory();
    }
}
