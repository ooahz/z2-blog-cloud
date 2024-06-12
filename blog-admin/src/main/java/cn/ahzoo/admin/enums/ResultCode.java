package cn.ahzoo.admin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    OBJECT_EMPTY(900100, "请求资源为空"),
    INVALID_PARAM(900101, "参数校验失败"),
    CONFLICT_FIELD(900109, "唯一性字段冲突"),
    INVALID_TOKEN(900110, "token验证失败"),
    DATA_OVERLOAD(900199, "资源已达最大数量"),

    RESOURCE_OVERLOAD(900309, "资源已达最大数量"),

    EXECUTION_ERROR(900401, "操作执行失败"),
    NOT_ALLOWED(900403, "不被允许的操作"),

    FREQUENT_ACCESS(900901, "请求过于频繁"),
    ;

    private final int code;
    private final String message;

}
