package cn.ahzoo.z2blog.controller;

import cn.ahzoo.utils.model.Result;
import cn.ahzoo.z2blog.model.vo.AuthorVO;
import cn.ahzoo.z2blog.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 博主信息视图
 * @github https://github.com/ooahz
 * @date 2024/6
 */
@RestController
@RequestMapping("v1/author")
@AllArgsConstructor
@Tag(name = "博主信息视图")
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "获取博主信息详细")
    @GetMapping("")
    public Result<AuthorVO> get() {
        AuthorVO authorVO = authorService.getDetail();
        return Result.success(authorVO);
    }

}
