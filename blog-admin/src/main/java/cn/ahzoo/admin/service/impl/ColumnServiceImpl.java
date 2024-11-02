package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.constant.Constant;
import cn.ahzoo.admin.model.dto.ColumnDTO;
import cn.ahzoo.admin.model.entity.Column;
import cn.ahzoo.admin.enums.ResultCode;
import cn.ahzoo.admin.mapper.ColumnMapper;
import cn.ahzoo.admin.model.mapstruct.ColumnMapping;
import cn.ahzoo.admin.model.vo.BriefColumnVO;
import cn.ahzoo.admin.model.vo.ColumnVO;
import cn.ahzoo.admin.service.ArticleColumnService;
import cn.ahzoo.admin.service.CategoryColumnService;
import cn.ahzoo.admin.service.ColumnService;
import cn.ahzoo.common.constant.RedisConstant;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column>
        implements ColumnService {

    private final ArticleColumnService articleColumnService;
    private final CategoryColumnService categoryColumnService;

    @Override
    public ResultList<List<ColumnVO>> listColumn(int pagination, String categoryId) {
        int paginationIndex = pagination - 1;
        List<ColumnVO> columnVOList = baseMapper.listColumnItem(
                paginationIndex * Constant.PAGE_SIZE,
                Constant.PAGE_SIZE, categoryId);
        return ResultList.success(new ResultPage(count(), columnVOList.size(), Constant.PAGE_SIZE, pagination),
                columnVOList);
    }

    @Override
    public ResultList<List<BriefColumnVO>> listAllColumn() {
        List<BriefColumnVO> list = baseMapper.listAll();
        return ResultList.success(ResultPage.emptyPage(), list);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstant.SYSTEM_STATISTICS_KEY, allEntries = true)
    @Override
    public Result<ColumnVO> saveColumn(ColumnDTO columnDTO) {
        Column column = ColumnMapping.INSTANCE.dto2Column(columnDTO);
        save(column);
        columnDTO.setId(column.getId());
        saveCategoryColumn(columnDTO);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<ColumnVO> updateColumn(ColumnDTO columnDTO) {
        Column column = ColumnMapping.INSTANCE.dto2Column(columnDTO);
        updateById(column);
        columnDTO.setId(column.getId());
        updateCategoryColumn(columnDTO);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstant.SYSTEM_STATISTICS_KEY, allEntries = true)
    @Override
    public Result<ColumnVO> deleteColumn(Long columnId) {
        Long count = articleColumnService.countArticleByColumnId(columnId);
        if (count > 0) {
            return Result.failed(ResultCode.NOT_ALLOWED.getCode(), "该专栏下有关联的文章");
        }
        baseMapper.removeByColumnId(columnId);
        return Result.success();
    }

    private void saveCategoryColumn(ColumnDTO columnDTO) {
        categoryColumnService.saveCategoryColumn(columnDTO);
    }

    private void updateCategoryColumn(ColumnDTO columnDTO) {
        categoryColumnService.updateCategoryColumn(columnDTO);
    }
}
