package cn.ahzoo.z2blog.service.impl;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl extends ServiceImpl<FriendMapper, Friend>
        implements FriendService {

    @Value("${feature.friend:true}")
    private boolean enableFriend;

    @Override
    public List<FriendVO> listFriends() {
        return baseMapper.listFriends();
    }

    @Override
    public Result<?> saveFriend(FriendDTO friendDTO, boolean isUpdated) {
        if (!enableFriend) {
            return Result.failed(ResultCode.NOT_ALLOWED.getCode(), "友链申请功能已关闭");
        }
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
