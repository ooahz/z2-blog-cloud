package cn.ahzoo.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserVO {
    private String email;
    private String name;
    private String password;
    private String salt;
}
