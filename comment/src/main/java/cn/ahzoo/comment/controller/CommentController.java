package cn.ahzoo.comment.controller;


import cn.ahzoo.comment.model.entity.Comment;
import cn.ahzoo.comment.model.vo.CommentVO;
import cn.ahzoo.comment.service.CommentService;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description 评论视图
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("v1/comments")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{articleId}")
    public ResultList<List<CommentVO>> getCommentByArticleId(@PathVariable String articleId,
                                                             @RequestParam(value = "p") @Min(value = 1, message = "页码不能小于1") int pagination) {
        return commentService.selectByArticleId(articleId, pagination);
    }

    @PostMapping("")
    public Result<?> saveComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }

    @GetMapping("/top")
    public ResultList<?> getTopComment() {
        return commentService.selectTop();
    }

}
