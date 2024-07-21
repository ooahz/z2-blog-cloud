package cn.ahzoo.z2blog.service.impl;

import cn.ahzoo.z2blog.constant.Constant;
import cn.ahzoo.z2blog.mapper.ArticleMapper;
import cn.ahzoo.z2blog.model.vo.ArticleItemVO;
import cn.ahzoo.z2blog.service.ArticleColumnService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleColumnServiceImpl implements ArticleColumnService {

    private final ArticleMapper articleMapper;

    @Override
    public List<ArticleItemVO> listArticleByColumnId(long id, int pagination) {
        return listArticleByColumnId(id, pagination, Constant.PAGE_SIZE);
    }

    @Override
    public List<ArticleItemVO> listArticleByColumnId(long id, int pagination, int pageSize) {
        int paginationIndex = pagination - 1;
        return articleMapper.listArticleByColumnId(id,
                paginationIndex * Constant.PAGE_SIZE, pageSize);
    }

    @Override
    public Long countArticleByColumnId(long columnId) {
        return articleMapper.countArticleByColumnId(columnId);
    }
}
