package cn.ahzoo.search.service;

import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.vo.ArticleItemVO;
import java.util.List;

public interface SearchService {

    ResultList<List<ArticleItemVO>> searchArticle(String keyword, int pagination);
}
