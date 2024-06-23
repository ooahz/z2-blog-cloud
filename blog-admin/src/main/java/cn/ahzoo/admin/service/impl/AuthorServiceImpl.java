package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.mapper.AuthorMapper;
import cn.ahzoo.admin.model.entity.Author;
import cn.ahzoo.admin.model.mapstruct.AuthorMapping;
import cn.ahzoo.admin.model.vo.AuthorVO;
import cn.ahzoo.admin.service.AuthorService;
import cn.ahzoo.common.constant.RedisConstant;
import cn.ahzoo.utils.model.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl extends ServiceImpl<AuthorMapper, Author>
        implements AuthorService {

    @Override
    public Result<AuthorVO> getDetail() {
        Author author = baseMapper.selectAuthorLimitOne();
        AuthorVO authorVO = AuthorMapping.INSTANCE.author2AuthorVO(author);
        return Result.success(authorVO);
    }

    @CacheEvict(value = RedisConstant.BLOG_AUTHOR_KEY, allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<?> updateAuthor(AuthorVO authorVO) {
        Author author = AuthorMapping.INSTANCE.authorVO2Author(authorVO);
        // 去除头尾引号
        author.setExtendsParams(StringUtils.strip(author.getExtendsParams(), "\""));
        saveOrUpdate(author);
        return Result.success();
    }
}




