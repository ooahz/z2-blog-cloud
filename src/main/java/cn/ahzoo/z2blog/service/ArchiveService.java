package cn.ahzoo.z2blog.service;

import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.vo.ArchiveItemVO;
import cn.ahzoo.z2blog.model.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ArchiveService extends IService<Article> {
    ResultList<List<ArchiveItemVO>> listArchive(int pagination);
}
