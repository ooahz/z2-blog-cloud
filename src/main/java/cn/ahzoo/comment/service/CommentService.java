package cn.ahzoo.comment.service;

import cn.ahzoo.comment.model.dto.CommentDTO;
import cn.ahzoo.comment.model.entity.Comment;
import cn.ahzoo.comment.model.vo.CommentVO;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CommentService extends IService<Comment> {

    ResultList<List<CommentVO>> selectByArticleId(String articleId, int page);

    Result<?> saveComment(CommentDTO commentDTO);

    ResultList<?> selectTop();

    void sendEmail(CommentDTO commentDTO);
}
