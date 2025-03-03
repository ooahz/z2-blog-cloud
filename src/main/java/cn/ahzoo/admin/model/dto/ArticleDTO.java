package cn.ahzoo.admin.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ArticleDTO {
    private Long id;
    private String path;

    @NotBlank(message = "标题不能为空")
    private String title;

    @NotBlank(message = "内容不能为空")
    private String content;

    @NotNull(message = "未选择专栏")
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
