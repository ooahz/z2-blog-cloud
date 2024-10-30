package cn.ahzoo.gateway.config;


import cn.ahzoo.gateway.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;
import java.util.Scanner;

/**
 * @description 自定义gateway异常信息
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private final Logger logger = LoggerFactory.getLogger(CustomErrorAttributes.class);

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request, ErrorAttributeOptions.defaults());
        logger.error("gateway异常:{}", errorAttributes);
        return Map.of(
                "state", "false",
                "code", ResultCode.FALLBACK_ACCESS.getCode(),
                "message", ResultCode.FALLBACK_ACCESS.getMessage()
        );
    }
}
