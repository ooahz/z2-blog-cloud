package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.entity.Author;
import cn.ahzoo.admin.model.vo.AuthorVO;
import cn.ahzoo.utils.model.Result;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AuthorService extends IService<Author> {

    Result<AuthorVO> getDetail();

    Result<?> updateAuthor(AuthorVO authorVO);

}
