package cn.ahzoo.admin.service;

import cn.ahzoo.admin.model.dto.FriendDTO;
import cn.ahzoo.admin.model.entity.Friend;
import cn.ahzoo.admin.model.vo.FriendVO;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface FriendService extends IService<Friend> {

    ResultList<List<FriendVO>> listFriends();

    Result<?> saveFriend(FriendDTO friendDTO);

    Result<?> deleteFriend(Long id);

    Result<?> updateFriend(FriendDTO friendDTO);
}
