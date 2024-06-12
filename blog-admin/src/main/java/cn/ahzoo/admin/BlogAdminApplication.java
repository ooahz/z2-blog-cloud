package cn.ahzoo.admin;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description 微服务项目————Admin
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@EnableAsync
@EnableCaching
@EnableFeignClients
@SpringBootApplication
public class BlogAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());
    }

}
