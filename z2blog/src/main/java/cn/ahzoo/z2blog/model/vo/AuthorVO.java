package cn.ahzoo.z2blog.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String name;
    private String siteName;
    private String website;
    private String avatar;
    private String email;
    private String description;
    private String extendsParams;
}
