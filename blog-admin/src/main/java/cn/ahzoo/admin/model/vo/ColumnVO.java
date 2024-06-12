package cn.ahzoo.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ColumnVO {
    private Long id;
    private String name;
    private String description;
    private String thumbnail;
    private List<CategoryVO> categoryList;
    private List<Long> categoryIds;
}
