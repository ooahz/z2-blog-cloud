package cn.ahzoo.z2blog.service.impl;

import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import cn.ahzoo.z2blog.constant.Constant;
import cn.ahzoo.z2blog.model.vo.ArchiveItemVO;
import cn.ahzoo.z2blog.mapper.ArticleMapper;
import cn.ahzoo.z2blog.model.entity.Article;
import cn.ahzoo.z2blog.service.ArchiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArchiveService {

    @Override
    public ResultList<List<ArchiveItemVO>> listArchive(int pagination) {
        int paginationIndex = pagination - 1;
        List<ArchiveItemVO> archiveItemVOS = baseMapper.listArchiveItem(paginationIndex * Constant.PAGE_SIZE, Constant.PAGE_SIZE);
        long countArticle = baseMapper.countArticle();
        return ResultList.success(
                new ResultPage(countArticle, archiveItemVOS.size(), Constant.PAGE_SIZE, pagination),
                archiveItemVOS);
    }
}
