package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.vo.ColumnVO;

public interface CategoryColumnService {

    /**
     * 统计分类下的专栏数量
     */
    Long countColumnByCategoryId(Long categoryId);

    /**
     * 保存分类下的专栏
     */
    void saveCategoryColumn(ColumnVO columnVO);

    /**
     * 更新分类下的专栏
     */
    void updateCategoryColumn(ColumnVO columnVO);
}
