package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.vo.ArticleVO;
import cn.ahzoo.utils.model.ResultList;

import java.util.List;

public interface SearchService {

    ResultList<List<ArticleVO>> searchAll(String keyword, int pagination);
}
