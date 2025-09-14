package cn.ahzoo.comment.controller;


import cn.ahzoo.comment.model.dto.CommentDTO;
import cn.ahzoo.comment.model.vo.CommentVO;
import cn.ahzoo.comment.service.CommentService;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 评论视图
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Tag(name = "评论模块——评论视图")
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/comments")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "获取文章详情评论")
    @GetMapping("/{articleId}")
    public ResultList<List<CommentVO>> getCommentByArticleId(@PathVariable String articleId,
                                                             @RequestParam(value = "p") @Min(value = 1, message = "页码不能小于1") int pagination) {
        return commentService.selectByArticleId(articleId, pagination);
    }

    @Operation(summary = "保存评论")
    @PostMapping("")
    public Result<?> saveComment(@RequestBody CommentDTO commentDTO) {
        Result<?> result = commentService.saveComment(commentDTO);
        commentService.sendEmail(commentDTO);
        return result;
    }

    @Operation(summary = "获取最新评论")
    @GetMapping("/top")
    public ResultList<?> getTopComment() {
        return commentService.selectTop();
    }
}
