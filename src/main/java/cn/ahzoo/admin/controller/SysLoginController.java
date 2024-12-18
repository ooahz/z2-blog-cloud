package cn.ahzoo.admin.controller;

import cn.ahzoo.admin.annotation.SystemLogger;
import cn.ahzoo.admin.model.entity.User;
import cn.ahzoo.admin.model.vo.UserInfoVO;
import cn.ahzoo.admin.model.vo.UserVO;
import cn.ahzoo.admin.service.UserService;
import cn.ahzoo.utils.model.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 登录功能
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@RestController
@RequestMapping("v1")
@AllArgsConstructor
@Tag(name = "系统-登录视图")
public class SysLoginController {

    private final UserService userService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    @SystemLogger(value = "登录用户", param = "user")
    public Result<UserInfoVO> login(@RequestBody User user) {
        return userService.login(user);
    }

    @Operation(summary = "登出")
    @PostMapping("/logout")
    public Result<UserVO> logout(@RequestBody User user) {
        return userService.logout(user);
    }
}
