package cn.ahzoo.admin.model.mapstruct;

import cn.ahzoo.admin.model.dto.UserDTO;
import cn.ahzoo.admin.model.entity.User;
import cn.ahzoo.admin.model.vo.UserInfoVO;
import cn.ahzoo.admin.model.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @description 用户相关实体类转换
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Mapper
public interface UserMapping {
    UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);

    User dto2User(UserDTO userDTO);

    UserInfoVO user2UserInfoVO(User user);
}
