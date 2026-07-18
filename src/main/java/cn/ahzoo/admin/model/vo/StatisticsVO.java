package cn.ahzoo.admin.model.vo;

import lombok.Data;

@Data
public class StatisticsVO {
    private ArticleStatisticsVO articles;
    private Long columns;
    private Long friends;
}
