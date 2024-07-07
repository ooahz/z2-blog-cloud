package cn.ahzoo.comment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 健康检查视图层
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@RestController
@RequestMapping("health")
public class HealthController {

    @GetMapping("")
    public String health() {
        return "ok";
    }

}
