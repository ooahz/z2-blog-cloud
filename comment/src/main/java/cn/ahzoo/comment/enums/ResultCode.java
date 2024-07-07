package cn.ahzoo.comment.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description 前缀400为当前微服务代码
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    OBJECT_EMPTY(400100, "请求资源为空"),
    INVALID_PARAM(400101, "参数校验失败"),
    CONFLICT_FIELD(400109, "唯一性字段冲突"),
    ;

    private final int code;
    private final String message;

}
