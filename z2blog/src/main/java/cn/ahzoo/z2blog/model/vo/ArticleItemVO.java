package cn.ahzoo.z2blog.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ArticleItemVO {
    private Long id;
    private String path;
    private String title;
    private String description;
    private String thumbnail;
    private Date createdDate;
    private Date updatedDate;
    private List<BriefColumnVO> columnList;
}
