package cn.ahzoo.common.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class IpUtil {

    private static final Logger logger = LoggerFactory.getLogger(IpUtil.class);

    private static final String[] headerNames = new String[]{"X-Real-IP", "x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = "";
        for (String headName : headerNames) {
            ipAddress = request.getHeader(headName);
            if (!StringUtils.isEmpty(ipAddress) && !"unknown".equalsIgnoreCase(ipAddress)) {
                break;
            }
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress) || "0:0:0:0:0:0:0:1".equals(ipAddress)) {
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    logger.error("ip地址获取失败:{}", e.getMessage());
                }
                ipAddress = inet.getHostAddress();
            }
        }
        return ipAddress;
    }
}
