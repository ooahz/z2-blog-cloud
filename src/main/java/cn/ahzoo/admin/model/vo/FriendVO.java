package cn.ahzoo.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FriendVO {
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
