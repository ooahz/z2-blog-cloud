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
 * @author 十玖八柒（Ahzoo）
 * @description 定时持久化访问量
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
    @Scheduled(cron = "0 30 1 * * ?")
    @Async
    public void access() {
        executeWithExceptionHandling(accessService::updateWebSiteAccess, "持久化网站访问量失败");
        executeWithExceptionHandling(accessService::updateArticleAccess, "持久化文章访问量失败");
    }

    private void executeWithExceptionHandling(Runnable task, String errorMessage) {
        try {
            task.run();
        } catch (Exception e) {
            logger.error("{}: {}", errorMessage, e.getMessage());
        }
    }
}
