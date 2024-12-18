package cn.ahzoo.z2blog.service;

import cn.ahzoo.z2blog.model.entity.AccessArticle;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AccessArticleService extends IService<AccessArticle> {

    /**
     * 持久化文章访问量
     */
    void updateArticleAccess();
}
