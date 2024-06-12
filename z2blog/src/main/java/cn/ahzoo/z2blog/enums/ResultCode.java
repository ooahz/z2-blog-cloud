package cn.ahzoo.z2blog.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    OBJECT_EMPTY(200100, "请求资源为空"),
    INVALID_PARAM(200101, "参数校验失败"),
    CONFLICT_FIELD(200109, "唯一性字段冲突"),
    DATA_OVERLOAD(200199, "资源已达最大数量"),

    RESOURCE_OVERLOAD(200309, "资源已达最大数量"),

    FREQUENT_ACCESS(200901, "请求过于频繁"),

    ;

    private final int code;
    private final String message;

}
