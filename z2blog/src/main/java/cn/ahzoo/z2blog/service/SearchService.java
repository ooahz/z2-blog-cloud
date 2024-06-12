package cn.ahzoo.z2blog.service;

import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.vo.ArticleVO;

import java.util.List;

public interface SearchService {

    ResultList<List<ArticleVO>> searchAll(String keyword, int pagination);

    ResultList<List<ArticleVO>> searchByTitle(String keyword, int pagination);

    ResultList<List<ArticleVO>> searchByContent(String keyword, int pagination);
}
