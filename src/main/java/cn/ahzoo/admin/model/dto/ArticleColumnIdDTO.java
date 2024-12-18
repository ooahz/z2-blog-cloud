package cn.ahzoo.admin.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArticleColumnIdDTO {
    Long articleId;
    List<Long> columnIds;
}
