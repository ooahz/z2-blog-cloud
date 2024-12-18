package cn.ahzoo.z2blog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 健康检查
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Tag(name = "健康检查")
@RestController
@RequestMapping("health")
public class HealthController {

    @Operation(summary = "健康检查,默认返回ok")
    @GetMapping("")
    public String health() {
        return "ok";
    }
}
