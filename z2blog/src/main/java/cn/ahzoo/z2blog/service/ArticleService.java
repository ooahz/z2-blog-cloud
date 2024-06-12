package cn.ahzoo.z2blog.service;

import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.entity.Article;
import cn.ahzoo.z2blog.model.vo.ArticleVO;
import cn.ahzoo.z2blog.model.vo.ArticleItemVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface ArticleService extends IService<Article> {

    ResultList<List<ArticleItemVO>> listArticle(int pagination);

    Result<ArticleVO> getArticleDetail(String articlePath);

    /**
     * 获取专栏界面下的文章列表
     */
    ResultList<List<ArticleItemVO>> listArticleByColumnId(long columnId, int pagination);
}
