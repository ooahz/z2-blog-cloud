package cn.ahzoo.z2blog.service.impl;

import cn.ahzoo.common.constant.RedisConstant;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.z2blog.enums.ResultCode;
import cn.ahzoo.z2blog.mapper.FriendMapper;
import cn.ahzoo.z2blog.model.dto.FriendDTO;
import cn.ahzoo.z2blog.model.entity.Friend;
import cn.ahzoo.z2blog.model.mapstruct.FriendMapping;
import cn.ahzoo.z2blog.model.vo.FriendVO;
import cn.ahzoo.z2blog.service.FriendService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
        implements FriendService {

    @Cacheable(RedisConstant.BLOG_FRIENDS_KEY)
    @Override
    public List<FriendVO> listFriends() {
        return baseMapper.listFriends();
    }

    @Override
    public Result<?> saveFriend(FriendDTO friendDTO, boolean isUpdated) {
        Friend friend = FriendMapping.INSTANCE.dto2Friend(friendDTO);
        if (isUpdated) {
            Friend dbFriend = baseMapper.selectUpdateByWebsite(friendDTO.getOldWebsite());
            if (ObjectUtils.isNotEmpty(dbFriend)) {
                return Result.failed(ResultCode.CONFLICT_FIELD.getCode(), "友链更新信息已提交，请等待更新完成！");
            }
            friend.setUpdateStatus();
        } else {
            Friend dbFriend = baseMapper.selectByWebsite(friendDTO.getWebsite());
            if (ObjectUtils.isNotEmpty(dbFriend)) {
                return Result.failed(ResultCode.CONFLICT_FIELD.getCode(), "友链已存在，如需更新，请在上方选择更新！");
            }
            friend.setReviewedStatus();
        }
        save(friend);
        return Result.success();
    }
}
