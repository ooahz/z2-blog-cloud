package cn.ahzoo.admin.model.mapstruct;

import cn.ahzoo.admin.model.dto.CategoryDTO;
import cn.ahzoo.admin.model.entity.Category;
import cn.ahzoo.admin.model.vo.CategoryVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description 分类相关实体类转换
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Mapper
public interface CategoryMapping {
    CategoryMapping INSTANCE = Mappers.getMapper(CategoryMapping.class);

    CategoryVO category2VO(Category category);

    Category dto2Category(CategoryDTO categoryDTO);

    List<CategoryVO> list2VOs(List<Category> categoryList);
}
