package cn.ahzoo.admin.controller;

import cn.ahzoo.admin.model.dto.UserDTO;
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
 * @description 用户功能
 * @github https://github.com/ooahz
 * @date 2024/12
 */
@RestController
@RequestMapping("v1/a/users")
@AllArgsConstructor
@Tag(name = "用户视图")
public class UserController {

    private final UserService userService;

    @Operation(summary = "重置密码")
    @PostMapping("/reset")
    public Result<?> resetPassword(@RequestBody UserDTO userDTO) {
        return userService.resetPassword(userDTO);
    }
}
