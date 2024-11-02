package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.mapper.SysLogMapper;
import cn.ahzoo.admin.model.entity.SysLog;
import cn.ahzoo.admin.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog>
        implements SysLogService {
}




