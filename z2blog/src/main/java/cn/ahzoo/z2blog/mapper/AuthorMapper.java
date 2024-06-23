package cn.ahzoo.z2blog.mapper;

import cn.ahzoo.z2blog.model.entity.Author;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AuthorMapper extends BaseMapper<Author> {

    Author selectAuthorLimitOne();

}
