package cn.ahzoo.admin.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName sys_log
 */
@TableName(value = "sys_log")
@Data
@Builder
public class SysLog implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private Long userId;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 请求方式
     */
    private String requestType;

    /**
     * 请求源地址
     */
    private String requestUrl;

    /**
     * 请求源ip
     */
    private String requestIp;

    /**
     * 请求参数
     */
    private String param;

    /**
     *
     */
    private String content;

    /**
     * 操作时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 写入时间
     */
    @TableField(value = "updated_time", fill = FieldFill.INSERT)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
