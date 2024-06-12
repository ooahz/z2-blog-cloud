package cn.ahzoo.z2blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName b_article
 */
@TableName(value = "b_article")
@Data
public class Article implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     * 文章路径
     */
    private String path;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章概述
     */
    private String description;

    /**
     * 文章预览图
     */
    private String thumbnail;

    /**
     * 文章页背景样式
     */
    private String style;

    /**
     *
     */
    private Date createdDate;

    /**
     *
     */
    private Date updatedDate;

    /**
     * 类型（1：原创，2：转载）
     */
    private Integer type;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 状态（1：正常，2：隐藏，3：草稿）
     */
    private Integer status;

    /**
     * 废弃（1：废弃，0：正常）
     */
    private Integer deprecated;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
