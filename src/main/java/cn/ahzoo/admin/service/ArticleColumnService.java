package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.dto.ArticleDTO;
import cn.ahzoo.admin.model.dto.BriefColumnDTO;

import java.util.List;

public interface ArticleColumnService {

    List<BriefColumnDTO> listColumnByArticleId(Long articleId);

    /**
     * 统计专栏下的文章数量
     */
    Long countArticleByColumnId(Long columnId);

    /**
     * 保存与文章关联的专栏
     */
    void saveArticleColumn(ArticleDTO articleDTO);

    void updateArticleColumn(ArticleDTO articleDTO);

    /**
     * 删除与文章id关联的专栏
     */
    void removeArticleColumnByArticleId(Long articleId);
}
