package cn.ahzoo.z2blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @description 微服务项目————Z2Blog
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/3
 * @code 200
 */
@EnableFeignClients
@EnableAsync
@EnableCaching
@SpringBootApplication
public class Z2blogApplication {

    public static void main(String[] args) {
        SpringApplication.run(Z2blogApplication.class, args);
    }

}
