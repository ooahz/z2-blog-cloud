package cn.ahzoo.z2blog.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CategoryColumnVO {
    private Long id;
    private String name;
    private List<BriefColumnVO> columnList;
}
