package cn.ahzoo.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName b_category
 */
@TableName(value = "b_category")
@Data
public class Category implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     * 分类名
     */
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
