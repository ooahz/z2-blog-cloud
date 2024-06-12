package cn.ahzoo.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName b_access
 */
@TableName(value = "b_access")
@Data
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
    private Integer uv;

    /**
     *
     */
    private Integer pv;

    /**
     * 类型(1:全站,2:文章)
     */
    private Integer type;

    /**
     * 数据统计的时间
     */
    private Date date;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
