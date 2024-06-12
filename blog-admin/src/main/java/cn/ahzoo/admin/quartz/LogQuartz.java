package cn.ahzoo.admin.quartz;

import cn.ahzoo.admin.service.SysLogService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description 定时持久化日志
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Component
@AllArgsConstructor
@EnableScheduling
public class LogQuartz {

    private static final Logger logger = LoggerFactory.getLogger(LogQuartz.class);

    private final SysLogService sysLogService;

    /**
     * 每天凌晨2点持久化日志
     */
    @Scheduled(cron = "0 0 2 * * ?")
    @Async
    public void access() {
        try {
            sysLogService.saveLog();
        } catch (Exception e) {
            logger.error("持久化日志失败: {}", e.getMessage());
        }
    }

}
