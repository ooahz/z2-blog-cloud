package cn.ahzoo.z2blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @TableName b_author
 */
@TableName(value = "b_author")
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
