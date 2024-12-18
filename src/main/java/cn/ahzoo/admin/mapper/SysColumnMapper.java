package cn.ahzoo.admin.mapper;

import cn.ahzoo.admin.model.entity.Column;
import cn.ahzoo.admin.model.dto.CategoryColumnIdDTO;
import cn.ahzoo.admin.model.dto.BriefColumnDTO;
import cn.ahzoo.admin.model.vo.BriefColumnVO;
import cn.ahzoo.admin.model.vo.ColumnVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysColumnMapper extends BaseMapper<Column> {

    List<BriefColumnDTO> listByArticleId(Long articleId);

    Long countColumnByCategoryId(Long categoryId);

   List<ColumnVO> listColumnItem(int paginationIndex, int size, String categoryId);

    List<BriefColumnVO> listAll();

    List<Long> listColumnIdsByArticleId(Long articleId);

    void insertCategoryColumn(CategoryColumnIdDTO saveCategoryColumnIdDTO);

    void removeCategoryColumn(CategoryColumnIdDTO removeCategoryColumnIdDTO);

    void removeByColumnId(Long columnId);
}




