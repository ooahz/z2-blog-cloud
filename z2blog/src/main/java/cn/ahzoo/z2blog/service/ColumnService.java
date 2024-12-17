package cn.ahzoo.z2blog.service;

import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.z2blog.model.entity.Column;
import cn.ahzoo.z2blog.model.vo.ColumnInfoVO;
import cn.ahzoo.z2blog.model.vo.ColumnItemVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ColumnService extends IService<Column> {

    /**
     * 获取分类界面下的专栏列表
     */
    ResultList<List<ColumnItemVO>> listColumnByCategoryId(long categoryId, int pagination);

    /**
     * 获取文章界面下的专栏列表
     */
    ResultList<List<ColumnItemVO>> listColumnByArticleId(long articleId);

    Result<ColumnInfoVO> getColumnInfoByName(String name);

    ResultList<List<ColumnItemVO>> listAllColumn();
}
