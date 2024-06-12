package cn.ahzoo.admin.controller;

import cn.ahzoo.utils.model.ResultList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 前端动态路由
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@RestController
@RequestMapping("v1/a/routes")
@AllArgsConstructor
@Tag(name = "动态路由")
public class RouteController {

    @Operation(summary = "获取前端动态路由")
    @GetMapping("")
    public ResultList<?> routes() {
        return ResultList.success();
    }
}
