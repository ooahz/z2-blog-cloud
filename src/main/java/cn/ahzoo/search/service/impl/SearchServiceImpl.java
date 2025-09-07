package cn.ahzoo.search.service.impl;

import cn.ahzoo.admin.constant.Constant;
import cn.ahzoo.search.service.SearchService;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import cn.ahzoo.z2blog.enums.ResultCode;
import cn.ahzoo.z2blog.mapper.ArticleMapper;
import cn.ahzoo.z2blog.model.vo.ArticleItemVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final ArticleMapper articleMapper;

    @Value("${feature.search:true}")
    private boolean enableSearch;

    @Override
    public ResultList<List<ArticleItemVO>> searchArticle(String keyword, int pagination) {
        if (!enableSearch) {
            return ResultList.failed(ResultCode.NOT_ALLOWED.getCode(), "搜索功能已关闭", ResultPage.emptyPage(), null);
        }
        int paginationIndex = pagination - 1;
        // 此搜索方式性能不佳，仅作演示，建议使用其它全文搜索工具替换
        List<ArticleItemVO> articleVOS = articleMapper.searchArticle(paginationIndex * Constant.PAGE_SIZE,
                Constant.PAGE_SIZE, keyword);
        return ResultList.success(ResultPage.emptyPage(), articleVOS);
    }
}
