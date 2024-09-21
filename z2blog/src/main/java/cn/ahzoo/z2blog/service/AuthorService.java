package cn.ahzoo.z2blog.service;

import cn.ahzoo.z2blog.model.entity.Author;
import cn.ahzoo.z2blog.model.vo.AuthorVO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AuthorService extends IService<Author> {

    AuthorVO getDetail();
}
