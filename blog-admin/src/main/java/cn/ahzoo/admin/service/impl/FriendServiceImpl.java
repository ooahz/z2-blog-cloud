package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.enums.ResultCode;
import cn.ahzoo.admin.exception.BizException;
import cn.ahzoo.admin.mapper.FriendMapper;
import cn.ahzoo.admin.model.entity.Friend;
import cn.ahzoo.admin.model.mapstruct.FriendMapping;
import cn.ahzoo.admin.model.vo.FriendVO;
import cn.ahzoo.admin.service.FriendService;
import cn.ahzoo.common.constant.RedisConstant;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
        implements FriendService {

    @Override
    public ResultList<List<FriendVO>> listFriends() {
        List<Friend> friendList = list();
        List<FriendVO> friendVOList = FriendMapping.INSTANCE.friendList2VOs(friendList);
        return ResultList.success(ResultPage.emptyPage(), friendVOList);
    }

    @CacheEvict(value = RedisConstant.BLOG_FRIENDS_KEY, allEntries = true)
    @Override
    public Result<?> saveFriend(FriendVO friendVO) {
        Friend friend = FriendMapping.INSTANCE.friendVO2Friend(friendVO);
        save(friend);
        return Result.success();
    }

    @CacheEvict(value = RedisConstant.BLOG_FRIENDS_KEY, allEntries = true)
    @Override
    public Result<?> deleteFriend(Long id) {
        removeById(id);
        return Result.success();
    }

    @CacheEvict(value = RedisConstant.BLOG_FRIENDS_KEY, allEntries = true)
    @Override
    public Result<?> updateFriend(FriendVO friendVO) {
        updateParamsValidate(friendVO);
        Friend friend = FriendMapping.INSTANCE.friendVO2Friend(friendVO);
        updateById(friend);
        return Result.success();
    }

    private void updateParamsValidate(FriendVO friendVO) {
        if (ObjectUtils.isEmpty(friendVO.getId())) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "友链ID为空");
        }
    }
}




