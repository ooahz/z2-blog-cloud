package cn.ahzoo.admin.model.mapstruct;

import cn.ahzoo.admin.model.dto.ColumnDTO;
import cn.ahzoo.admin.model.entity.Column;
import cn.ahzoo.admin.model.vo.ColumnVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description 专栏相关实体类转换
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Mapper
public interface ColumnMapping {
    ColumnMapping INSTANCE = Mappers.getMapper(ColumnMapping.class);

    Column dto2Column(ColumnDTO columnDTO);
}
