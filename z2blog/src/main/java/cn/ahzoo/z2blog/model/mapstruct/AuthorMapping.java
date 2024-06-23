package cn.ahzoo.z2blog.model.mapstruct;

import cn.ahzoo.z2blog.model.entity.Author;
import cn.ahzoo.z2blog.model.vo.AuthorVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 博主信息相关实体类转换
 * @github https://github.com/ooahz
 * @date 2024/6
 */
@Mapper
public interface AuthorMapping {

    AuthorMapping INSTANCE = Mappers.getMapper(AuthorMapping.class);

    Author authorVO2Author(AuthorVO authorVO);

    AuthorVO author2AuthorVO(Author author);

}
