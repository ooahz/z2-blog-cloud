package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.dto.UserDTO;
import cn.ahzoo.admin.model.entity.User;
import cn.ahzoo.admin.model.vo.ArticleVO;
import cn.ahzoo.admin.model.vo.UserInfoVO;
import cn.ahzoo.admin.model.vo.UserVO;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UserService extends IService<User> {

    Result<UserInfoVO> login(User user);

    Result<UserVO> logout(User user);

    Result<?> resetPassword(UserDTO userDTO);
}
