package cn.ahzoo.z2blog.service;

import cn.ahzoo.utils.model.Result;
import cn.ahzoo.z2blog.model.entity.Friend;
import cn.ahzoo.z2blog.model.vo.FriendVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface FriendService extends IService<Friend> {

    List<FriendVO> listFriends();

    Result<?> saveFriend(FriendVO friendVO, String isUpdated);
}
