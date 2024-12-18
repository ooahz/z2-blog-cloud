package cn.ahzoo.admin.mapper;

import cn.ahzoo.admin.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper extends BaseMapper<Role> {

    List<String> listRoleCodeByUserId(Long userId);
}




