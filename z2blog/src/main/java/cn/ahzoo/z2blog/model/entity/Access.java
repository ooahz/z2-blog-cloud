package cn.ahzoo.z2blog.model.entity;

import com.baomidou.mybatisplus.annotation.*;
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
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     *
     */
    private Long uv;

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

    public Access(Long uv, Long pv, String date) {
        this.uv = uv;
        this.pv = pv;
        this.date = date;
    }

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
