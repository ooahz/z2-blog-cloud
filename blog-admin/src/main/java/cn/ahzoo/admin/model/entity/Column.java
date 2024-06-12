package cn.ahzoo.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName b_column
 */
@TableName(value = "b_column")
@Data
public class Column implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     * 专栏名
     */
    private String name;

    /**
     * 专栏简介
     */
    private String description;

    /**
     * 专栏缩略图
     */
    private String thumbnail;

    /**
     * 专栏背景样式
     */
    private String style;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
