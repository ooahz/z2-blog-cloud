package cn.ahzoo.z2blog.service;

import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.entity.Category;
import cn.ahzoo.z2blog.model.vo.CategoryColumnVO;
import cn.ahzoo.z2blog.model.vo.CategoryVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CategoryService extends IService<Category> {

    ResultList<List<CategoryVO>> listCategory();

    /**
     * 获取文章所属分类及专栏
     */
    ResultList<List<CategoryColumnVO>> listCategoryColumnByArticleId(long articleId);
}
