package cn.ahzoo.admin.model.mapstruct;

import cn.ahzoo.admin.model.dto.FriendDTO;
import cn.ahzoo.admin.model.entity.Friend;
import cn.ahzoo.admin.model.vo.FriendVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description 友链相关实体类转换
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Mapper
public interface FriendMapping {
    FriendMapping INSTANCE = Mappers.getMapper(FriendMapping.class);

    List<FriendVO> list2VOs(List<Friend> friendList);

    Friend dto2Friend(FriendDTO friendDTO);
}
