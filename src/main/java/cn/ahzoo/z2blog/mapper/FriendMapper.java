package cn.ahzoo.z2blog.mapper;

import cn.ahzoo.z2blog.model.entity.Friend;
import cn.ahzoo.z2blog.model.vo.FriendVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FriendMapper extends BaseMapper<Friend> {

    List<FriendVO> listFriends();

    Friend selectByWebsite(String website);

    Friend selectUpdateByWebsite(String website);
}
