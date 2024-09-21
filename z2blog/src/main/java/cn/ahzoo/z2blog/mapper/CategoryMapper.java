package cn.ahzoo.z2blog.mapper;

import cn.ahzoo.z2blog.model.entity.Category;
import cn.ahzoo.z2blog.model.vo.CategoryColumnVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}




