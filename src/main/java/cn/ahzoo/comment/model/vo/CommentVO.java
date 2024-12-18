package cn.ahzoo.comment.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommentVO {
    private Long id;
    private Long parentId;
    private String userAvatar;
    private String userName;
    private String userWebsite;
    private String replyName;
    private String tagName;
    private String content;
    private String area;
    private String userAgent;
    private Date createdDate;
    private List<CommentVO> child;
}
