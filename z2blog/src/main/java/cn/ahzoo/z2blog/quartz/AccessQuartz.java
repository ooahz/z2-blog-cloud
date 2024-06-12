package cn.ahzoo.z2blog.quartz;

import cn.ahzoo.z2blog.service.AccessService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description 定时持久化访问量
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Component
@AllArgsConstructor
@EnableScheduling
public class AccessQuartz {

    private final AccessService accessService;
    private static final Logger logger = LoggerFactory.getLogger(AccessQuartz.class);

    /**
     * 每天凌晨1点持久化访问量
     */
    @Scheduled(cron = "0 0 1 * * ?")
    @Async
    public void access() {
        try {
            accessService.updateWebSiteAccess();
            accessService.updateArticleAccess();
        } catch (Exception e) {
            logger.error("持久化访问量失败: {}", e.getMessage());
        }
    }
}
