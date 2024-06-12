package cn.ahzoo.z2blog.controller;

import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import cn.ahzoo.z2blog.model.vo.FriendVO;
import cn.ahzoo.z2blog.service.FriendService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 友链视图
 * @github https://github.com/ooahz
 * @date 2024/4
 */
@Tag(name = "友链视图")
@RestController
@RequestMapping("v1/friends")
@AllArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @Operation(summary = "获取友链列表")
    @GetMapping("")
    public ResultList<List<FriendVO>> list() {
        List<FriendVO> friendVOList = friendService.listFriends();
        return ResultList.success(ResultPage.emptyPage(), friendVOList);
    }

    @Operation(summary = "保存友链")
    @PostMapping("")
    public Result<?> save(@Validated @RequestBody FriendVO friendVO,
                          @RequestParam("u") String updatedStr) {
        return friendService.saveFriend(friendVO, updatedStr);
    }
}
