package cn.ahzoo.admin.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewStatisticsVO implements Serializable {
    private static final long serialVersionUID = 1L;
    Integer pv;
    Integer uv;
}
