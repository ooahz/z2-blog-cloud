package cn.ahzoo.gateway.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description 前缀200为当前微服务代码
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    FALLBACK_ACCESS(100909, "请求过于频繁"),
    ;

    private final int code;
    private final String message;

}
