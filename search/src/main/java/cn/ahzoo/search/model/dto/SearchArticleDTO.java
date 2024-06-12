package cn.ahzoo.search.model.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
public class SearchArticleDTO {

    @Id
    private String id;

    @Field(value = "articleId", type = FieldType.Long)
    private Long articleId;
}
