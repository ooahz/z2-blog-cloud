package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.entity.User;
import cn.ahzoo.admin.model.vo.UserInfoVO;
import cn.ahzoo.admin.model.vo.UserVO;
import cn.ahzoo.utils.model.Result;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {

    Result<UserInfoVO> login(User user);

    Result<UserVO> logout(User user);

    void updateUser(UserVO uservo);
}
