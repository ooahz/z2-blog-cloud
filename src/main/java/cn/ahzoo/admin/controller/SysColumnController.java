package cn.ahzoo.admin.controller;

import cn.ahzoo.admin.annotation.SystemLogger;
import cn.ahzoo.admin.model.dto.ColumnDTO;
import cn.ahzoo.admin.model.vo.BriefColumnVO;
import cn.ahzoo.admin.model.vo.ColumnVO;
import cn.ahzoo.admin.service.ColumnService;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 专栏相关
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Tag(name = "系统-专栏视图")
@RestController
@RequestMapping("v1/a/columns")
@AllArgsConstructor
public class SysColumnController {

    private final ColumnService columnService;

    @Operation(summary = "获取专栏列表分页")
    @GetMapping("")
    public ResultList<List<ColumnVO>> list(@RequestParam(value = "p") int pagination,
                                           @RequestParam(required = false) String categoryId) {
        return columnService.listColumn(pagination, categoryId);
    }

    @Operation(summary = "获取所有专栏数据")
    @GetMapping("/all")
    public ResultList<List<BriefColumnVO>> listAll() {
        return columnService.listAllColumn();
    }

    @Operation(summary = "保存专栏")
    @SaCheckRole("admin")
    @PostMapping("")
    public Result<ColumnVO> save(@RequestBody ColumnDTO columnDTO) {
        return columnService.saveColumn(columnDTO);
    }

    @Operation(summary = "更新专栏")
    @SaCheckRole("admin")
    @PutMapping("")
    @SystemLogger(value = "更新专栏")
    public Result<ColumnVO> update(@RequestBody ColumnDTO columnDTO) {
        return columnService.updateColumn(columnDTO);
    }

    @Operation(summary = "删除专栏")
    @SaCheckRole("admin")
    @DeleteMapping("/{columnId}")
    @SystemLogger(value = "删除专栏")
    public Result<ColumnVO> delete(@PathVariable Long columnId) {
        return columnService.deleteColumn(columnId);
    }
}
