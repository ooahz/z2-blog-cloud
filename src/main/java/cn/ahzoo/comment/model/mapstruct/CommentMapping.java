package cn.ahzoo.comment.model.mapstruct;

import cn.ahzoo.comment.model.dto.CommentDTO;
import cn.ahzoo.comment.model.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 评论相关实体类转换
 * @github https://github.com/ooahz
 * @date 2024/12
 */
@Mapper
public interface CommentMapping {
    CommentMapping INSTANCE = Mappers.getMapper(CommentMapping.class);

    Comment dto2Comment(CommentDTO commentDTO);
}
