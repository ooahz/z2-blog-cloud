package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.enums.ResultCode;
import cn.ahzoo.admin.exception.BizException;
import cn.ahzoo.admin.model.mapstruct.UserMapping;
import cn.ahzoo.admin.model.vo.UserInfoVO;
import cn.ahzoo.admin.model.vo.UserVO;
import cn.ahzoo.utils.model.Result;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ahzoo.admin.model.entity.User;
import cn.ahzoo.admin.service.UserService;
import cn.ahzoo.admin.mapper.UserMapper;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Override
    public Result<UserInfoVO> login(User user) {
        paramsValidate(user);
        User dbUser = baseMapper.selectUserByEmail(user.getEmail());
        passwordValidateAndLogin(user, dbUser);
        UserInfoVO userInfoVO = UserMapping.INSTANCE.user2UserInfoVO(dbUser);
        return Result.success(userInfoVO);
    }

    @Override
    public Result<UserVO> logout(User user) {
        StpUtil.logout();
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUser(UserVO uservo) {
        User user = UserMapping.INSTANCE.vo2User(uservo);
        paramsValidate(user);
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        String salt = HexUtil.encodeHexStr(RandomUtil.randomBytes(11));
        String digestPassword = digester.digestHex(uservo.getPassword() + salt);
        user.resetPassword(salt, digestPassword);
        updateById(user);
    }

    private void paramsValidate(User user) {
        if (ObjectUtils.isEmpty(user.getEmail()) || ObjectUtils.isEmpty(user.getPassword())) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "账号或密码不能为空");
        }
    }

    private void passwordValidateAndLogin(User user, User dbUser) {
        String password = user.getPassword();
        if (ObjectUtils.isEmpty(dbUser)) {
            throw new BizException(ResultCode.EXECUTION_ERROR.getCode(), "账号或密码有误");
        }
        String salt = dbUser.getSalt();
        String dbPassword = dbUser.getPassword();
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        String digestPassword = digester.digestHex(password + salt);
        if (StringUtils.equals(digestPassword, dbPassword)) {
            StpUtil.login(dbUser.getId());
        } else {
            throw new BizException(ResultCode.EXECUTION_ERROR.getCode(), "账号或密码有误");
        }
    }
}




