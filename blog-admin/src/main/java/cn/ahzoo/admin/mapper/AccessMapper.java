package cn.ahzoo.admin.mapper;

import cn.ahzoo.admin.model.entity.Access;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccessMapper extends BaseMapper<Access> {

    Access selectWebsiteAccess();
}




