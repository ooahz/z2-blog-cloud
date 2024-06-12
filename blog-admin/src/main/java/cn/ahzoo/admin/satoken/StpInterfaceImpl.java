package cn.ahzoo.admin.satoken;

import cn.ahzoo.admin.service.RoleService;
import cn.dev33.satoken.stp.StpInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description sa-token权限加载接口实现类
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Component
@AllArgsConstructor
public class StpInterfaceImpl implements StpInterface {

    private final RoleService roleService;

    /**
     * 返回一个账号所拥有的权限码集合（暂时未作权限校验）
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return Collections.emptyList();
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object userId, String loginType) {
        return roleService.listRoleCodeByUserId(Long.parseLong(String.valueOf(userId)));
    }
}
