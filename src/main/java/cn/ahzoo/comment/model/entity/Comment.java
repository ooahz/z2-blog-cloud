package cn.ahzoo.comment.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName b_comment
 */
@TableName(value = "b_comment")
@Data
public class Comment implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     * 文章标识
     */
    private String articleId;

    /**
     * 站点标识
     */
    private String website;

    /**
     * 父评论
     */
    private Long parentId;

    /**
     *
     */
    private String userAvatar;

    /**
     *
     */
    private String userName;

    /**
     *
     */
    private String userWebsite;

    /**
     *
     */
    private String userEmail;

    /**
     * 回复用户
     */
    private String replyName;

    /**
     *
     */
    private String content;

    /**
     *
     */
    private String ip;

    /**
     * 归属地
     */
    private String area;

    /**
     * 客户端
     */
    private String userAgent;

    /**
     *
     */
    @TableField(value = "created_date", fill = FieldFill.INSERT)
    private Date createdDate;

    /**
     * 状态(0:不可见,1:正常)
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
