package cn.ahzoo.z2blog.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ColumnItemVO {
    private Long id;
    private String name;
    private String description;
    private String thumbnail;
    private List<ArticleItemVO> articleList;
}
