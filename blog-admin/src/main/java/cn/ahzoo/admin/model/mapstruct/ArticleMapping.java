package cn.ahzoo.admin.model.mapstruct;

import cn.ahzoo.admin.model.dto.ArticleDTO;
import cn.ahzoo.admin.model.entity.Article;
import cn.ahzoo.admin.model.record.ArticleESRecord;
import cn.ahzoo.admin.model.vo.ArticleVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
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

    Article vo2Article(ArticleVO articleVO);

    Article dto2Article(ArticleDTO articleDTO);

    @Mapping(target = "articleId", source = "id")
    ArticleESRecord dto2ArticleESRecord(ArticleDTO articleDTO);

    List<ArticleVO> list2VOs(List<Article> articleList);
}
