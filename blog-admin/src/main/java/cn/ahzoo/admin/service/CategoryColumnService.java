package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.dto.ColumnDTO;

public interface CategoryColumnService {

    /**
     * 统计分类下的专栏数量
     */
    Long countColumnByCategoryId(Long categoryId);

    /**
     * 保存分类下的专栏
     */
    void saveCategoryColumn(ColumnDTO columnDTO);

    /**
     * 更新分类下的专栏
     */
    void updateCategoryColumn(ColumnDTO columnDTO);
}
