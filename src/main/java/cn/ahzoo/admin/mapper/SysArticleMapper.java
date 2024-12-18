package cn.ahzoo.admin.mapper;

import cn.ahzoo.admin.model.dto.ArticleDTO;
import cn.ahzoo.admin.model.entity.Article;
import cn.ahzoo.admin.model.dto.ArticleColumnIdDTO;
import cn.ahzoo.admin.model.dto.ArticleContentDTO;
import cn.ahzoo.admin.model.vo.ArticleItemVO;
import cn.ahzoo.admin.model.vo.ArticleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysArticleMapper extends BaseMapper<Article> {

    Long validateDuplicateCount(String title, String articlePath, Long id);

    Long countArticleByColumnId(Long columnId);

    void saveArticleContent(ArticleDTO articleDTO);

    void updateArticleContent(ArticleDTO articleDTO);

    List<ArticleItemVO> listArticleItem(int paginationIndex, int size, String status, String type, String columnId);

    ArticleVO selectArticleById(Long articleId);

    ArticleContentDTO selectArticleContentById(Long articleId);

    void insertArticleColumn(ArticleColumnIdDTO articleColumnIdDTO);

    void removeArticleColumn(ArticleColumnIdDTO articleColumnIdDTO);

    void removeArticleColumnByArticleId(Long articleId);

    Long countArticle();
}




