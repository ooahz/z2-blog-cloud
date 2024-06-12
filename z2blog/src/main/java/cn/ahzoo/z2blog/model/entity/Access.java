package cn.ahzoo.z2blog.model.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName b_access
 */
@TableName(value = "b_access")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Access implements Serializable {

    /**
     *
     */
    private Long id;

    /**
     *
     */
    @TableId
    private Long articleId;

    /**
     *
     */
    private Integer uv = 0;

    /**
     *
     */
    private Integer pv = 0;

    /**
     * 类型(1:全站,2:文章)
     */
    private Integer type;

    /**
     * 数据统计的时间
     */
    @TableField(value = "date", fill = FieldFill.INSERT_UPDATE)
    private Date date;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Access(Long articleId, Integer uv, Integer pv, Integer type) {
        this.articleId = articleId;
        this.uv = uv;
        this.pv = pv;
        this.type = type;
    }
}
