package cn.ahzoo.comment.service.impl;

import cn.ahzoo.comment.enums.ResultCode;
import cn.ahzoo.comment.exception.BizException;
import cn.ahzoo.comment.mapper.CommentMapper;
import cn.ahzoo.comment.model.dto.CommentDTO;
import cn.ahzoo.comment.model.entity.Comment;
import cn.ahzoo.comment.model.mapstruct.CommentMapping;
import cn.ahzoo.comment.model.vo.CommentVO;
import cn.ahzoo.comment.model.vo.TopCommentVO;
import cn.ahzoo.comment.service.CommentService;
import cn.ahzoo.comment.utils.MailSendUtil;
import cn.ahzoo.common.utils.IpUtil;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    private final HttpServletRequest request;
    private final MailSendUtil mailSendUtil;

    @Value("${author.mail.mine}")
    private String authorEmail;

    private static final String DEFAULT_USER_AVATAR = "data:image/webp;base64,iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAIAAAD8GO2jAAAAdElEQVR4Xu2RMQ6AMAwD+38x8kMk3sGAiExTN5aABfmUyY1zQ9v+Ma0P3uYHgmXdYvABc2VY14KySz85n5hP37+woBbk1XmBkS+cY0GQLwwErIA5wnYsCNiOJHiCBSU3AXvAXIF1LQhYVxJgruxgbsFwB/MD/P2sKIDHT2EAAAAASUVORK5CYII=";
    private static final int PAGE_SIZE = 20;

    @Override
    public ResultList<List<CommentVO>> selectByArticleId(String articleId, int pagination) {
        int paginationIndex = pagination - 1;
        List<CommentVO> commentList = baseMapper.selectByArticleId(articleId,
                paginationIndex * PAGE_SIZE, PAGE_SIZE);
        return ResultList.success(ResultPage.emptyPage(), commentList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<?> saveComment(CommentDTO commentDTO) {
        Comment comment = CommentMapping.INSTANCE.dto2Comment(commentDTO);
        paramsValidate(comment);
        buildComment(comment);
        save(comment);
        return Result.success("success");
    }

    @Override
    public ResultList<?> selectTop() {
        List<TopCommentVO> commentList = baseMapper.selectTop();
        return ResultList.success(ResultPage.emptyPage(), commentList);
    }

    @Async
    @Override
    public void sendEmail(CommentDTO comment) {
        String email;

        if (ObjectUtils.isEmpty(comment.getReplyId())) {
            // 发给博主本人
            email = authorEmail;
        } else {
            // 发给被回复用户
            Comment replyComment = getById(comment.getReplyId());
            email = replyComment.getUserEmail();
        }
        mailSendUtil.emailNotification(email);
    }

    private void paramsValidate(Comment comment) {
        String content = comment.getContent();
        String name = comment.getUserName();
        String email = comment.getUserEmail();
        String web = comment.getUserWebsite();

        if (StringUtils.isEmpty(content) || content.length() > 1000) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "评论内容超过限制");
        }
        if (StringUtils.isEmpty(name)) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "昵称为空");
        }
        if (name.length() > 25) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "昵称字数超过限制");
        }
        if (ObjectUtils.isNotEmpty(email) && email.length() > 36) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "邮箱字数超过限制");
        }
        if (ObjectUtils.isNotEmpty(web) && web.length() > 36) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "网址字数超过限制");
        }
        if (StringUtils.isEmpty(comment.getArticleId())) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "文章ID为空");
        }
    }

    private void buildComment(Comment comment) {
        String originWebsite = request.getHeader("Referer");
        String ipAddress = IpUtil.getIpAddress(request);
        String userAgent = IpUtil.getUserAgent(request);
        String ipArea = IpUtil.getIpArea(ipAddress);
        comment.setUserAgent(userAgent);
        comment.setIp(ipAddress);
        comment.setArea(ipArea);
        comment.setUserAvatar(DEFAULT_USER_AVATAR);
        comment.setWebsite(originWebsite);
    }
}
