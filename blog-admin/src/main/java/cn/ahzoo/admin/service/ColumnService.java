package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.entity.Column;
import cn.ahzoo.admin.model.vo.BriefColumnVO;
import cn.ahzoo.admin.model.vo.ColumnVO;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ColumnService extends IService<Column> {

    /**
     * 获取专栏的分页列表
     */
    ResultList<List<ColumnVO>> listColumn(int pagination, String categoryId);

    /**
     * 获取所有的专栏
     */
    ResultList<List<BriefColumnVO>> listAllColumn();

    Result<ColumnVO> saveColumn(ColumnVO columnVO);

    Result<ColumnVO> updateColumn(ColumnVO columnVO);

    Result<ColumnVO> deleteColumn(Long columnId);
}
