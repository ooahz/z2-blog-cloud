package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.model.dto.WebsiteAccessDTO;
import cn.ahzoo.admin.model.mapstruct.AccessMapping;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ahzoo.admin.model.entity.Access;
import cn.ahzoo.admin.service.AccessService;
import cn.ahzoo.admin.mapper.AccessMapper;
import org.springframework.stereotype.Service;

@Service
public class AccessServiceImpl extends ServiceImpl<AccessMapper, Access>
    implements AccessService{

    @Override
    public WebsiteAccessDTO getWebsiteAccess() {
        Access access = baseMapper.getWebsiteAccess();
        return AccessMapping.INSTANCE.access2WebsiteDTO(access);
    }
}




