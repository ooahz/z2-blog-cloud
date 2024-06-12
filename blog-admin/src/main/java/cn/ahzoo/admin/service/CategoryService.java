package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.entity.Category;
import cn.ahzoo.admin.model.vo.CategoryVO;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<Category> {

    ResultList<List<CategoryVO>> listCategory();

    Result<CategoryVO> saveCategory(CategoryVO categoryVO);

    Result<CategoryVO> updateCategory(CategoryVO categoryVO);

    Result<CategoryVO> deleteCategory(Long categoryId);
}