package cn.ahzoo.z2blog.service.impl;

import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import cn.ahzoo.z2blog.model.mapstruct.CategoryMapping;
import cn.ahzoo.z2blog.model.vo.CategoryColumnVO;
import cn.ahzoo.z2blog.model.vo.CategoryVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.ahzoo.z2blog.model.entity.Category;
import cn.ahzoo.z2blog.service.CategoryService;
import cn.ahzoo.z2blog.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {

    @Override
    public ResultList<List<CategoryVO>> listCategory() {
        List<Category> list = list();
        List<CategoryVO> categoryVOList = CategoryMapping.INSTANCE.categoryList2VOs(list);
        return ResultList.success(ResultPage.emptyPage(), categoryVOList);
    }

    @Override
    public ResultList<List<CategoryColumnVO>> listCategoryColumnByArticleId(long articleId) {
        List<CategoryColumnVO> categoryColumnVOList = baseMapper.listCategoryColumnByArticleId(articleId);
        return ResultList.success(ResultPage.emptyPage(), categoryColumnVOList);
    }

}




