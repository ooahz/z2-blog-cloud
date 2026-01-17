package cn.ahzoo.z2blog.controller;

import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.vo.ArchiveItemVO;
import cn.ahzoo.z2blog.service.ArchiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 归档视图
 * @github https://github.com/ooahz
 * @date 2026/1
 */
@Tag(name = "博客模块——文章归档")
@RestController
@RequestMapping("v1/archives")
@AllArgsConstructor
public class ArchiveController {

    private final ArchiveService archiveService;

    @Operation(summary = "获取文章归档列表")
    @GetMapping("")
    public ResultList<List<ArchiveItemVO>> list(@RequestParam(value = "p", defaultValue = "1")
                                                @Min(value = 1, message = "页码不能小于1") @Max(value = 1000, message = "页码不能大于1000")
                                                int pagination) {
        return archiveService.listArchive(pagination);
    }
}
