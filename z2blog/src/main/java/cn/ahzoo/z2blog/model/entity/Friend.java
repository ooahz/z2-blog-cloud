package cn.ahzoo.z2blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName b_friend
 */
@TableName(value = "b_friend")
@Data
public class Friend implements Serializable {
    /**
     *
     */
    @TableId
    private Long id;

    /**
     *
     */
    private String oldWebsite;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String website;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private String avatar;

    /**
     * 权重
     */
    private Integer weight;

    /**
     * 类型(1:默认,2:技术,3:生活）
     */
    private Integer type;

    /**
     * 状态(0:不可见,1:正常,2审核中,3待更新)
     */
    private Integer status;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public void beReviewed() {
        this.status = 2;
    }

    public void beUpdate() {
        this.status = 3;
    }
}
