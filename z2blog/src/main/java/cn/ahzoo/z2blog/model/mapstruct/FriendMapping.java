package cn.ahzoo.z2blog.model.mapstruct;

import cn.ahzoo.z2blog.model.entity.Friend;
import cn.ahzoo.z2blog.model.vo.FriendVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description 友链相关实体类转换
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/4
 */
@Mapper
public interface FriendMapping {

    FriendMapping INSTANCE = Mappers.getMapper(FriendMapping.class);

    Friend friendVO2Friend(FriendVO friendVO);
}
