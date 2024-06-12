package cn.ahzoo.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName sys_user
 */
@TableName(value = "sys_user")
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String salt;

    /**
     *
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
