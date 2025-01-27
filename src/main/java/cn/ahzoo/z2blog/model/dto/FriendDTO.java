package cn.ahzoo.z2blog.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
public class FriendDTO {
    private Long id;

    @NotBlank(message = "网址不能为空")
    private String website;

    private String oldWebsite;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String description;
    private String avatar;
    private Integer type;
}
