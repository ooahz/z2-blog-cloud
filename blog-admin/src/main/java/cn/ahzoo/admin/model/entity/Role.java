package cn.ahzoo.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName sys_role
 */
@TableName(value = "sys_role")
@Data
public class Role implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     *
     */
    private Long userId;

    /**
     *
     */
    private String roleCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
