package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.model.dto.CategoryDTO;
import cn.ahzoo.admin.model.entity.Category;
import cn.ahzoo.admin.enums.ResultCode;
import cn.ahzoo.admin.mapper.CategoryMapper;
import cn.ahzoo.admin.model.mapstruct.CategoryMapping;
import cn.ahzoo.admin.model.vo.CategoryVO;
import cn.ahzoo.admin.service.CategoryColumnService;
import cn.ahzoo.admin.service.CategoryService;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    private final CategoryColumnService categoryColumnService;

    @Override
    public ResultList<List<CategoryVO>> listCategory() {
        List<Category> list = list();
        List<CategoryVO> categoryVOS = CategoryMapping.INSTANCE.list2VOs(list);
        return ResultList.success(ResultPage.emptyPage(), categoryVOS);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<CategoryVO> saveCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapping.INSTANCE.dto2Category(categoryDTO);
        save(category);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<CategoryVO> updateCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapping.INSTANCE.dto2Category(categoryDTO);
        updateById(category);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<CategoryVO> deleteCategory(Long categoryId) {
        Long count = categoryColumnService.countColumnByCategoryId(categoryId);
        if (count > 0) {
            return Result.failed(ResultCode.NOT_ALLOWED.getCode(), "该分类下有关联的专栏");
        }
        removeById(categoryId);
        return Result.success();
    }
}
