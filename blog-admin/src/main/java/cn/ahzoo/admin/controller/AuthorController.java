package cn.ahzoo.admin.controller;

import cn.ahzoo.admin.annotation.SystemLogger;
import cn.ahzoo.admin.model.vo.AuthorVO;
import cn.ahzoo.admin.service.AuthorService;
import cn.ahzoo.utils.model.Result;
import cn.dev33.satoken.annotation.SaCheckRole;
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
@RequestMapping("v1/a/author")
@AllArgsConstructor
@Tag(name = "博主信息视图")
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "获取博主信息详细")
    @GetMapping("")
    public Result<AuthorVO> get() {
        return authorService.getDetail();
    }

    @Operation(summary = "更新博主信息")
    @SaCheckRole("admin")
    @PutMapping("")
    @SystemLogger(value = "更新信息")
    public Result<?> update(@RequestBody AuthorVO authorVO) {
        return authorService.updateAuthor(authorVO);
    }
}
