package cn.ahzoo.admin.model.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class StatisticsVO implements Serializable {
    private static final long serialVersionUID = 1L;

    ArticleStatisticsVO articles;
    Long columnsTotal;
    ViewStatisticsVO views;
    ViewStatisticsVO yesterdayViews;
}
