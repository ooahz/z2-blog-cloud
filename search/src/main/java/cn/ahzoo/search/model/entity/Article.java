package cn.ahzoo.search.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Builder
@Document(indexName = "article")
public class Article {

    @Id
    private String id;

    @Field(value = "articleId", type = FieldType.Long)
    private Long articleId;

    // analyzer指定插入数据时的分词器；searchAnalyzer指定搜索数据时的分词器
    // ik分词器有两种策略，ik_max_word（穷举分割），ik_smart（智能分割）
    @Field(value = "title", type = FieldType.Keyword,
            analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String title;

    @Field(value = "content", type = FieldType.Text,
            analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String content;
}
