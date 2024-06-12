package cn.ahzoo.admin.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WebsiteAccessDTO {
    private Integer uv;
    private Integer pv;
    private Date date;
}
