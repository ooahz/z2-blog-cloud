package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoleService extends IService<Role> {

    List<String> listRoleCodeByUserId(Long userId);
}
