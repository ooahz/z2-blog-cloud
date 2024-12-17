package cn.ahzoo.z2blog.model.mapstruct;

import cn.ahzoo.z2blog.model.entity.Article;
import cn.ahzoo.z2blog.model.vo.ArticleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @description 文章相关实体类转换
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/3
 */
@Mapper
public interface ArticleMapping {

    ArticleMapping INSTANCE = Mappers.getMapper(ArticleMapping.class);

    List<ArticleVO> list2VOs(List<Article> articleList);
}
