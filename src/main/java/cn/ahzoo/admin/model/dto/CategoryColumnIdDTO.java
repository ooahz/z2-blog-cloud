package cn.ahzoo.admin.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryColumnIdDTO {
    Long columnId;
    List<Long> categoryIds;
}
