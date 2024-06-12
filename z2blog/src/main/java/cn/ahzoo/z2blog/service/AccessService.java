package cn.ahzoo.z2blog.service;

import cn.ahzoo.z2blog.model.entity.Access;
import com.baomidou.mybatisplus.extension.service.IService;


public interface AccessService extends IService<Access> {

    /**
     * 缓存网站访问量
     */
    void cacheWebsitePVAndUV(String ipAddress);

    /**
     * 缓存文章访问量
     */
    void cacheArticlePVAndUV(Long articleId);

    /**
     * 持久化网站访问量
     */
    void updateWebSiteAccess();

    /**
     * 持久化文章访问量
     */
    void updateArticleAccess();
}
