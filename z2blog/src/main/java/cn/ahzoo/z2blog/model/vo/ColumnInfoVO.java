package cn.ahzoo.z2blog.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ColumnInfoVO {
    private Long id;
    private String name;
    private String thumbnail;
    private String description;
    private Long total;
    private String style;
    private List<CategoryVO> categoryList;
}
