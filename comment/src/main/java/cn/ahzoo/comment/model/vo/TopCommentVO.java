package cn.ahzoo.comment.model.vo;

import lombok.Data;

@Data
public class TopCommentVO {
    private String userName;
    private String replyName;
    private String website;
    private String content;
}
