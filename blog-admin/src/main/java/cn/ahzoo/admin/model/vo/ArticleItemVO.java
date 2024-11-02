package cn.ahzoo.admin.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleItemVO {
    private Long id;
    private String title;
    private Date createdDate;
    private Date updatedDate;
    private Integer type;
    private Integer weight;
    private Integer status;
}
