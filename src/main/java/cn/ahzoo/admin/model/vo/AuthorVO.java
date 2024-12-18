package cn.ahzoo.admin.model.vo;

import lombok.Data;

@Data
public class AuthorVO {
    private Long id;
    private String name;
    private String siteName;
    private String website;
    private String avatar;
    private String email;
    private String description;
    private String extendsParams;
}
