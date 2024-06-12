package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.mapper.ArticleMapper;
import cn.ahzoo.admin.mapper.ColumnMapper;
import cn.ahzoo.admin.model.dto.ArticleColumnIdDTO;
import cn.ahzoo.admin.model.dto.ColumnDTO;
import cn.ahzoo.admin.model.vo.ArticleVO;
import cn.ahzoo.admin.service.ArticleColumnService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleColumnServiceImpl implements ArticleColumnService {

    private final ColumnMapper columnMapper;
    private final ArticleMapper articleMapper;

    @Override
    public List<ColumnDTO> listColumnByArticleId(Long articleId) {
        return columnMapper.listByArticleId(articleId);
    }

    @Override
    public Long countArticleByColumnId(Long columnId) {
        return articleMapper.countArticleByColumnId(columnId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveArticleColumn(ArticleVO articleVO) {
        List<Long> columnIds = articleVO.getColumnIds();
        if (ObjectUtils.isEmpty(columnIds) || columnIds.size() == 0) {
            return;
        }
        ArticleColumnIdDTO saveArticleColumnIdDTO = ArticleColumnIdDTO.builder()
                .articleId(articleVO.getId())
                .columnIds(columnIds)
                .build();
        articleMapper.insertArticleColumn(saveArticleColumnIdDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateArticleColumn(ArticleVO articleVO) {
        List<Long> columnIds = articleVO.getColumnIds();
        List<Long> oldColumnIds = columnMapper.listColumnIdsByArticleId(articleVO.getId());
        ArrayList<Long> existIds = new ArrayList<>();
        ArrayList<Long> removeIds = new ArrayList<>();
        oldColumnIds.forEach(oldColumnId -> {
            if (!columnIds.contains(oldColumnId)) {
                removeIds.add(oldColumnId);
            } else {
                existIds.add(oldColumnId);
            }
        });
        columnIds.removeAll(existIds);
        if (!columnIds.isEmpty()) {
            ArticleColumnIdDTO saveArticleColumnIdDTO = ArticleColumnIdDTO.builder()
                    .articleId(articleVO.getId())
                    .columnIds(columnIds)
                    .build();
            articleMapper.insertArticleColumn(saveArticleColumnIdDTO);
        }
        if (removeIds.isEmpty()) {
            return;
        }
        ArticleColumnIdDTO removeArticleColumnIdDTO = ArticleColumnIdDTO.builder()
                .articleId(articleVO.getId())
                .columnIds(removeIds)
                .build();
        articleMapper.removeArticleColumn(removeArticleColumnIdDTO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeArticleColumnByArticleId(Long articleId) {
        articleMapper.removeArticleColumnByArticleId(articleId);
    }
}
