package cn.ahzoo.z2blog.service.impl;

import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import cn.ahzoo.z2blog.constant.Constant;
import cn.ahzoo.z2blog.mapper.ColumnMapper;
import cn.ahzoo.z2blog.model.entity.Column;
import cn.ahzoo.z2blog.model.vo.ColumnInfoVO;
import cn.ahzoo.z2blog.model.vo.ColumnItemVO;
import cn.ahzoo.z2blog.model.vo.ArticleItemVO;
import cn.ahzoo.z2blog.service.ArticleColumnService;
import cn.ahzoo.z2blog.service.ColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column>
        implements ColumnService {

    private final ArticleColumnService articleColumnService;

    @Override
    public ResultList<List<ColumnItemVO>> listColumnByCategoryId(long categoryId, int pagination) {
        int paginationIndex = pagination - 1;
        List<ColumnItemVO> columnItemVOList = baseMapper
                .listColumnItemByCategoryId(categoryId, paginationIndex, Constant.PAGE_SIZE);
        int count = baseMapper.countByCategoryId(categoryId);
        return ResultList.success(
                ResultPage.defaultPage(count, pagination),
                columnItemVOList);
    }

    @Override
    public ResultList<List<ColumnItemVO>> listColumnByArticleId(long articleId) {
        List<ColumnItemVO> columnItemVOList = baseMapper
                .listColumnItemByArticleId(articleId);
        columnItemVOList.forEach(columnItemVO -> {
            List<ArticleItemVO> articleItemVOList = listArticleByColumnId(columnItemVO.getId());
            columnItemVO.setArticleList(articleItemVOList);
        });
        return ResultList.success(ResultPage.emptyPage(), columnItemVOList);
    }

    @Override
    public Result<ColumnInfoVO> getColumnInfoByName(String name) {
        ColumnInfoVO columnInfo = baseMapper.getColumnInfoByName(name);
        Long count = articleColumnService.countArticleByColumnId(columnInfo.getId());
        columnInfo.setTotal(count);
        return Result.success(columnInfo);
    }

    private List<ArticleItemVO> listArticleByColumnId(long columnId) {
        return articleColumnService.listArticleByColumnId(columnId, 1, 3);
    }
}




