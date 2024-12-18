package cn.ahzoo.z2blog.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ArticleVO {
    private Long id;
    private String path;
    private String title;
    private String description;
    private String content;
    private String thumbnail;
    private String style;
    private Date createdDate;
    private Date updatedDate;
    private Integer type;
}
