package cn.ahzoo.admin.mapper;

import cn.ahzoo.admin.model.entity.Author;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthorMapper extends BaseMapper<Author> {

    Author selectAuthorLimitOne();

}




