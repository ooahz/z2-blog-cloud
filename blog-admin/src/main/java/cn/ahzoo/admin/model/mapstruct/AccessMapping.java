package cn.ahzoo.admin.model.mapstruct;

import cn.ahzoo.admin.model.dto.WebsiteAccessDTO;
import cn.ahzoo.admin.model.entity.Access;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description 访问量相关实体类转换
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Mapper
public interface AccessMapping {
    AccessMapping INSTANCE = Mappers.getMapper(AccessMapping.class);

    WebsiteAccessDTO access2WebsiteDTO(Access access);
}
