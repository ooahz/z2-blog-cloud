package cn.ahzoo.z2blog.exception;


import cn.ahzoo.utils.enums.CommonResultCode;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.z2blog.enums.ResultCode;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotRoleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

/**
 * @description 全局异常处理
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@RestControllerAdvice
public class GlobalException {

    private final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    /**
     * 登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Result<?> notLoginException(NotLoginException e) {
        logger.warn(e.getMessage());
        return Result.failed(ResultCode.INVALID_TOKEN.getCode(), e.getMessage());
    }

    /**
     * 角色处理
     */
    @ExceptionHandler(NotRoleException.class)
    public Result<?> notRoleException(NotRoleException e) {
        logger.warn(e.getMessage());
        return Result.failed(ResultCode.NOT_ALLOWED.getCode(), "当前角色没有该操作权限");
    }

    /**
     * 业务异常处理
     */
    @ExceptionHandler(BizException.class)
    public Result<?> bizException(BizException e) {
        logger.warn(e.getMessage());
        return Result.failed(e.getCode(), e.getMessage());
    }

    /**
     * 空指针异常处理
     */
    @ExceptionHandler(NullPointerException.class)
    public Result<?> nullPointerException(NullPointerException e) {
        logger.error(e.getMessage(), e);
        return Result.failed(ResultCode.OBJECT_EMPTY.getCode(), "系统出错，参数为空");
    }

    /**
     * 参数检验异常
     */
    @ExceptionHandler(HandlerMethodValidationException.class)
    public Result<?> paramValidationException(HandlerMethodValidationException e) {
        String msg = "参数校验失败";
        try {
            msg = e.getValueResults().get(0).getResolvableErrors().get(0).getDefaultMessage();
        } catch (Exception ex) {
            logger.error("获取参数校验信息失败", e);
        }
        return Result.failed(CommonResultCode.SERVER_ERROR.getCode(), msg);
    }

    /**
     * 参数缺失
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> missingServletRequestParameter(MissingServletRequestParameterException e) {
        logger.error(e.getMessage(), e);
        return Result.failed(CommonResultCode.SERVER_ERROR.getCode(), "必要参数缺失");
    }

    /**
     * 全局异常处理
     */
    @ExceptionHandler(Exception.class)
    public Result<?> exception(Exception e) {
        logger.error(e.getMessage(), e);
        return Result.failed(CommonResultCode.SERVER_ERROR.getCode(), "系统出错");
    }
}
