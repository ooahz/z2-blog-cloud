package cn.ahzoo.admin.annotation;

import java.lang.annotation.*;

/**
 * {@link cn.ahzoo.admin.aop.LogAspect}
 * @description 自定义日志注解
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLogger {

    // 日志说明
    String value() default "";
    // 参数
    String param() default "";
}
