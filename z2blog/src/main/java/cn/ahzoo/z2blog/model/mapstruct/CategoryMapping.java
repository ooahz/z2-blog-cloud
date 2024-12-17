package cn.ahzoo.z2blog.model.mapstruct;

import cn.ahzoo.z2blog.model.entity.Category;
import cn.ahzoo.z2blog.model.vo.CategoryVO;
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

    List<CategoryVO> list2VOs(List<Category> categoryList);
}
