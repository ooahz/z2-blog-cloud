package cn.ahzoo.comment.mapper;

import cn.ahzoo.comment.model.entity.Comment;
import cn.ahzoo.comment.model.vo.CommentVO;
import cn.ahzoo.comment.model.vo.TopCommentVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentVO> selectByArticleId(String articleId, int paginationIndex, int size);

    List<TopCommentVO> selectTop();
}




