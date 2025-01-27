package cn.ahzoo.z2blog.config;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Configuration;

/**
 * @author 十玖八柒（Ahzoo）
 * @description JSON数据类型转换，Long类型转为String类型
 * @github https://github.com/ooahz
 * @date 2024/7
 */
@Configuration
public class JsonModuleConfig extends SimpleModule {

    public JsonModuleConfig() {
        // 序列化
        this.addSerializer(Long.class, ToStringSerializer.instance);
        this.addSerializer(Long.TYPE, ToStringSerializer.instance);
    }
}

