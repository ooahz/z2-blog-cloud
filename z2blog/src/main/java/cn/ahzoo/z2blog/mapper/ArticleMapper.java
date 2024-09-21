package cn.ahzoo.z2blog.mapper;

import cn.ahzoo.z2blog.model.dto.ArticleContentDTO;
import cn.ahzoo.z2blog.model.entity.Article;
import cn.ahzoo.z2blog.model.vo.ArticleVO;
import cn.ahzoo.z2blog.model.vo.ArticleItemVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {

    List<ArticleItemVO> listArticleByColumnId(long columnId, int paginationIndex, int size);

    List<ArticleVO> selectBatchByIds(List<Long> ids);

    Long countArticleByColumnId(long columnId);

    List<ArticleItemVO> listArticleItem(int paginationIndex, int size);

    ArticleContentDTO selectArticleContentById(long articleId);

    ArticleVO getArticleByPath(String path);

    long countArticle();
}




