package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.dto.SysLogDTO;
import cn.ahzoo.admin.model.entity.SysLog;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysLogService extends IService<SysLog> {

    /**
     * 将操作日志保存到缓存中
     */
    void saveCache(SysLogDTO log);

    /**
     * 持久化操作日志
     */
    void saveLog();
}
