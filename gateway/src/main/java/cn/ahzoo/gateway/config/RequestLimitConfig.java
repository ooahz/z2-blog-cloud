package cn.ahzoo.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @description gateway限流规则
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Configuration
public class RequestLimitConfig {

    /**
     * 最多只能同时配置一个自定义的KeyResolver
     * 方法对应limit配置文件
     */
    @Bean
    KeyResolver allRequest() {
        return exchange -> Mono.just("all");
    }
}
