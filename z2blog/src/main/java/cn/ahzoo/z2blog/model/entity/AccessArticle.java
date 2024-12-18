package cn.ahzoo.z2blog.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName b_access_article
 */
@TableName(value = "b_access_article")
@Data
public class AccessArticle implements Serializable {

    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Long articleId;

    /**
     *
     */
    private Long pv;

    /**
     * 数据统计的时间
     */
    private String date;

    /**
     * 数据写入时间
     */
    @TableField(value = "updated_date", fill = FieldFill.INSERT_UPDATE)
    private Date updatedDate;

    public AccessArticle(Long articleId, Long pv, String date) {
        this.articleId = articleId;
        this.pv = pv;
        this.date = date;
    }

    private static final long serialVersionUID = 1L;
}
