package cn.ahzoo.z2blog.model.mapstruct;

import cn.ahzoo.z2blog.model.dto.FriendDTO;
import cn.ahzoo.z2blog.model.entity.Friend;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 友链相关实体类转换
 * @github https://github.com/ooahz
 * @date 2024/4
 */
@Mapper
public interface FriendMapping {

    FriendMapping INSTANCE = Mappers.getMapper(FriendMapping.class);

    Friend dto2Friend(FriendDTO friendDTO);
}
