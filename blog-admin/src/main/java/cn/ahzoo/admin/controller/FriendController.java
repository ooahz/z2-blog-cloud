package cn.ahzoo.admin.controller;

import cn.ahzoo.admin.annotation.SystemLogger;
import cn.ahzoo.admin.model.dto.FriendDTO;
import cn.ahzoo.admin.model.vo.FriendVO;
import cn.ahzoo.admin.service.FriendService;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 友链
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@RestController
@RequestMapping("v1/a/friends")
@AllArgsConstructor
@Tag(name = "友链视图")
public class FriendController {

    private final FriendService friendService;

    @Operation(summary = "获取友链列表")
    @GetMapping("")
    public ResultList<List<FriendVO>> list() {
        return friendService.listFriends();
    }

    @Operation(summary = "新增友链")
    @SaCheckRole("admin")
    @PostMapping("")
    @SystemLogger(value = "新增友链")
    public Result<?> save(@Validated @RequestBody FriendDTO friendDTO) {
        return friendService.saveFriend(friendDTO);
    }

    @Operation(summary = "更新友链")
    @SaCheckRole("admin")
    @PutMapping("")
    @SystemLogger(value = "更新友链")
    public Result<?> update(@Validated @RequestBody FriendDTO friendDTO) {
        return friendService.updateFriend(friendDTO);
    }

    @Operation(summary = "删除友链")
    @SaCheckRole("admin")
    @DeleteMapping("/{id}")
    @SystemLogger(value = "删除友链")
    public Result<?> delete(@PathVariable Long id) {
        return friendService.deleteFriend(id);
    }
}
