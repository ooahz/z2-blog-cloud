package cn.ahzoo.admin.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ArticleVO {
    private Long id;
    private String path;
    private String title;
    private String content;
    private List<Long> columnIds;
    private String description;
    private String htmlContent;
    private String thumbnail;
    private String style;
    private Date createdDate;
    private Date updatedDate;
    private Integer weight;
    private Integer type;
    private Integer status;
}
