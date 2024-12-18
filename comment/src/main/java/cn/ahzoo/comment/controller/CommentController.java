package cn.ahzoo.comment.controller;


import cn.ahzoo.comment.model.dto.CommentDTO;
import cn.ahzoo.comment.model.vo.CommentVO;
import cn.ahzoo.comment.service.CommentService;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
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
    public Result<?> saveComment(@RequestBody CommentDTO commentDTO) {
        Result<?> result = commentService.saveComment(commentDTO);
        commentService.sendEmail(commentDTO);
        return result;
    }

    @GetMapping("/top")
    public ResultList<?> getTopComment() {
        return commentService.selectTop();
    }
}
