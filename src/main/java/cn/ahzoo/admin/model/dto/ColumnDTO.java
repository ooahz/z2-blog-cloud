package cn.ahzoo.admin.model.dto;

import cn.ahzoo.admin.model.vo.CategoryVO;
import lombok.Data;

import java.util.List;

@Data
public class ColumnDTO {
    private Long id;
    private String name;
    private String description;
    private String thumbnail;
    private List<CategoryVO> categoryList;
    private List<Long> categoryIds;
}
