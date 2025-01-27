package cn.ahzoo.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ahzoo.admin.model.entity.Role;
import cn.ahzoo.admin.service.RoleService;
import cn.ahzoo.admin.mapper.SysRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, Role>
    implements RoleService{

    @Override
    public List<String> listRoleCodeByUserId(Long userId) {
        return baseMapper.listRoleCodeByUserId(userId);
    }
}
