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
    private String website;
    private String oldWebsite;
    private String name;
    private String description;
    private String avatar;
    private Integer type;
}
