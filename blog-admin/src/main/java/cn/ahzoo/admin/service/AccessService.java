package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.dto.WebsiteAccessDTO;
import cn.ahzoo.admin.model.entity.Access;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AccessService extends IService<Access> {

    /**
     * 获取网址访问量统计数据
     */
    WebsiteAccessDTO getWebsiteAccess();
}
