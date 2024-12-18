package cn.ahzoo.z2blog.mapper;

import cn.ahzoo.z2blog.model.entity.AccessArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccessArticleMapper extends BaseMapper<AccessArticle> {

    List<AccessArticle> selectBatchByArticleIds(List<Long> ids);
}




