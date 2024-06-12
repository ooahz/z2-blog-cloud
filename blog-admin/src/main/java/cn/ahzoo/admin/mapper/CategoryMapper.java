package cn.ahzoo.admin.mapper;

import cn.ahzoo.admin.model.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    List<Long> listCategoryIdsByColumnId(Long columnId);
}




