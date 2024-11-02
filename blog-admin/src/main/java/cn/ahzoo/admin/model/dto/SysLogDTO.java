package cn.ahzoo.admin.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SysLogDTO {
    private Long userId;
    private String methodName;
    private String requestType;
    private String requestUrl;
    private String requestIp;
    private String param;
    private String content;
    private LocalDateTime createdTime;
}
