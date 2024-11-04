package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.mapper.CategoryMapper;
import cn.ahzoo.admin.mapper.ColumnMapper;
import cn.ahzoo.admin.model.dto.CategoryColumnIdDTO;
import cn.ahzoo.admin.model.dto.ColumnDTO;
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
    public void saveCategoryColumn(ColumnDTO columnDTO) {
        List<Long> categoryIds = columnDTO.getCategoryIds();
        CategoryColumnIdDTO saveCategoryColumnIdDTO = CategoryColumnIdDTO.builder()
                .columnId(columnDTO.getId())
                .categoryIds(categoryIds)
                .build();
        columnMapper.insertCategoryColumn(saveCategoryColumnIdDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCategoryColumn(ColumnDTO columnDTO) {
        List<Long> categoryIds = columnDTO.getCategoryIds();
        List<Long> oldCategoryIds = categoryMapper.listCategoryIdsByColumnId(columnDTO.getId());
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
                    .columnId(columnDTO.getId())
                    .categoryIds(categoryIds)
                    .build();
            columnMapper.insertCategoryColumn(saveCategoryColumnIdDTO);
        }
        if (removeIds.isEmpty()) {
            return;
        }
        CategoryColumnIdDTO removeCategoryColumnIdDTO = CategoryColumnIdDTO.builder()
                .columnId(columnDTO.getId())
                .categoryIds(removeIds)
                .build();
        columnMapper.removeCategoryColumn(removeCategoryColumnIdDTO);
    }
}
