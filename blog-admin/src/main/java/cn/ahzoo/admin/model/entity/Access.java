package cn.ahzoo.admin.model.entity;

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
    private Long id;

    private Integer uv;

    private Integer pv;

    private String date;

    private Date updatedDate;

    private static final long serialVersionUID = 1L;
}
