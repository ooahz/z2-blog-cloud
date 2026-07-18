package cn.ahzoo.admin.controller;

import cn.ahzoo.admin.model.vo.StatisticsVO;
import cn.ahzoo.admin.service.StatisticsService;
import cn.ahzoo.utils.model.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 数据统计
 * @github https://github.com/ooahz
 * @date 2026/7
 */
@RestController
@RequestMapping("v1/a/statistics")
@AllArgsConstructor
@Tag(name = "系统模块——统计视图")
public class SysStatisticsController {

    private final StatisticsService statisticsService;

    @Operation(summary = "数据统计")
    @GetMapping("")
    public Result<StatisticsVO> statistics() {
        return Result.success(statisticsService.getStatistics());
    }
}
