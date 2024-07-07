package cn.ahzoo.comment.exception;

import cn.ahzoo.utils.enums.CommonResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BizException extends RuntimeException {

    private Integer code;
    private String message;

    public BizException(String message) {
        this.code = CommonResultCode.BIZ_ERROR.getCode();
        this.message = message;
    }
}
