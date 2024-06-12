package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.dto.ColumnDTO;
import cn.ahzoo.admin.model.vo.ArticleVO;

import java.util.List;

public interface ArticleColumnService {

    List<ColumnDTO> listColumnByArticleId(Long articleId);

    /**
     * 统计专栏下的文章数量
     */
    Long countArticleByColumnId(Long columnId);

    /**
     * 保存与文章关联的专栏
     */
    void saveArticleColumn(ArticleVO articleVO);

    void updateArticleColumn(ArticleVO articleVO);

    /**
     * 删除与文章id关联的专栏
     */
    void removeArticleColumnByArticleId(Long articleId);
}
