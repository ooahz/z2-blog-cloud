package cn.ahzoo.z2blog.service.impl;

import cn.ahzoo.common.constant.RedisConstant;
import cn.ahzoo.z2blog.mapper.AuthorMapper;
import cn.ahzoo.z2blog.model.entity.Author;
import cn.ahzoo.z2blog.model.mapstruct.AuthorMapping;
import cn.ahzoo.z2blog.model.vo.AuthorVO;
import cn.ahzoo.z2blog.service.AuthorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author>
        implements AuthorService {

    @Cacheable(RedisConstant.BLOG_AUTHOR_KEY)
    @Override
    public AuthorVO getDetail() {
        Author author = baseMapper.selectAuthorLimitOne();
        return AuthorMapping.INSTANCE.author2AuthorVO(author);
    }
}




