package cn.ahzoo.comment.model.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long replyId;
    private String articleId;
    private String website;
    private Long parentId;
    private String userAvatar;
    private String userName;
    private String userWebsite;
    private String userEmail;
    private String replyName;
    private String content;
}
