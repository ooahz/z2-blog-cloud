package cn.ahzoo.z2blog.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FriendVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
