package cn.ahzoo.admin.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FriendDTO {
    private Long id;

    @NotBlank(message = "网址不能为空")
    private String website;

    @NotBlank(message = "名称不能为空")
    private String name;

    private String description;
    private String avatar;
    private Integer weight;
    private Integer status;
    private Integer type;
}
