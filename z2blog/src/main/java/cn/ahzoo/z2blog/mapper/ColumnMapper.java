package cn.ahzoo.z2blog.mapper;

import cn.ahzoo.z2blog.model.entity.Column;
import cn.ahzoo.z2blog.model.vo.CategoryColumnVO;
import cn.ahzoo.z2blog.model.vo.ColumnInfoVO;
import cn.ahzoo.z2blog.model.vo.ColumnItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ColumnMapper extends BaseMapper<Column> {

    List<ColumnItemVO> listColumnItemByCategoryId(long categoryId, int paginationIndex, int size);

    List<ColumnItemVO> listColumnItemByArticleId(long articleId);

    ColumnInfoVO selectColumnInfoByName(String name);

    Long countByCategoryId(long categoryId);
}




