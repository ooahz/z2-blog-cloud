package cn.ahzoo.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName b_author
 */
@TableName(value ="b_author")
@Data
public class Author implements Serializable {
    private Long id;

    private String name;

    private String siteName;

    private String website;

    private String avatar;

    private String email;

    private String description;

    private String extendsParams;

    private static final long serialVersionUID = 1L;
}
