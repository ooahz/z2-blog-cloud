package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.mapper.CategoryMapper;
import cn.ahzoo.admin.mapper.ColumnMapper;
import cn.ahzoo.admin.model.dto.CategoryColumnIdDTO;
import cn.ahzoo.admin.model.vo.ColumnVO;
import cn.ahzoo.admin.service.CategoryColumnService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryColumnServiceImpl implements CategoryColumnService {

    private final ColumnMapper columnMapper;
    private final CategoryMapper categoryMapper;

    @Override
    public Long countColumnByCategoryId(Long categoryId) {
        return columnMapper.countColumnByCategoryId(categoryId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveCategoryColumn(ColumnVO columnVO) {
        List<Long> categoryIds = columnVO.getCategoryIds();
        CategoryColumnIdDTO saveCategoryColumnIdDTO = CategoryColumnIdDTO.builder()
                .columnId(columnVO.getId())
                .categoryIds(categoryIds)
                .build();
        columnMapper.insertCategoryColumn(saveCategoryColumnIdDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCategoryColumn(ColumnVO columnVO) {
        List<Long> categoryIds = columnVO.getCategoryIds();
        List<Long> oldCategoryIds = categoryMapper.listCategoryIdsByColumnId(columnVO.getId());
        ArrayList<Long> existIds = new ArrayList<>();
        ArrayList<Long> removeIds = new ArrayList<>();
        oldCategoryIds.forEach(oldCategoryId -> {
            if (!categoryIds.contains(oldCategoryId)) {
                removeIds.add(oldCategoryId);
            } else {
                existIds.add(oldCategoryId);
            }
        });
        categoryIds.removeAll(existIds);
        if (!categoryIds.isEmpty()) {
            CategoryColumnIdDTO saveCategoryColumnIdDTO = CategoryColumnIdDTO.builder()
                    .columnId(columnVO.getId())
                    .categoryIds(categoryIds)
                    .build();
            columnMapper.insertCategoryColumn(saveCategoryColumnIdDTO);
        }
        if (removeIds.isEmpty()) {
            return;
        }
        CategoryColumnIdDTO removeCategoryColumnIdDTO = CategoryColumnIdDTO.builder()
                .columnId(columnVO.getId())
                .categoryIds(removeIds)
                .build();
        columnMapper.removeCategoryColumn(removeCategoryColumnIdDTO);
    }
}
