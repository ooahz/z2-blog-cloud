package cn.ahzoo.admin.mapper;

import cn.ahzoo.admin.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<User> {

    User selectUserByEmail(String email);
}




