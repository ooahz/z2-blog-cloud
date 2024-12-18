package cn.ahzoo.z2blog.model.mapstruct;

import cn.ahzoo.z2blog.model.entity.Column;
import cn.ahzoo.z2blog.model.vo.ColumnItemVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 专栏相关实体类转换
 * @github https://github.com/ooahz
 * @date 2024/12
 */
@Mapper
public interface ColumnMapping {

    ColumnMapping INSTANCE = Mappers.getMapper(ColumnMapping.class);

    List<ColumnItemVO> list2VOs(List<Column> columnList);
}
