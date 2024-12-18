package cn.ahzoo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 十玖八柒（Ahzoo）
 * @description Z次元博客——Lite版本
 * @github https://github.com/ooahz
 * @date 2024/12
 */
@EnableAsync
@SpringBootApplication(scanBasePackages = {"cn.ahzoo.z2blog", "cn.ahzoo.search", "cn.ahzoo.comment", "cn.ahzoo.admin"})
@MapperScan({"cn.ahzoo.z2blog.mapper", "cn.ahzoo.comment.mapper", "cn.ahzoo.admin.mapper"})
public class Z2blogApplication {

    public static void main(String[] args) {
        SpringApplication.run(Z2blogApplication.class, args);
    }

}
