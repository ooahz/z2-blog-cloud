package cn.ahzoo.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserInfoVO {
    private String email;
    private String name;
}
