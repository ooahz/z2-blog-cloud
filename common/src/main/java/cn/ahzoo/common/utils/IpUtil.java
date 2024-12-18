package cn.ahzoo.common.utils;

import eu.bitwalker.useragentutils.UserAgent;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class IpUtil {

    private static final Logger logger = LoggerFactory.getLogger(IpUtil.class);

    private static final String[] headerNames = new String[]{"X-Real-IP", "x-forwarded-for", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR"};

    private static DbSearcher dbSearcher;
    private static Method method;

    @PostConstruct
    private void init() throws IOException, DbMakerConfigException, NoSuchMethodException {
        InputStream inputStream = new ClassPathResource("/ip/ip2region.db").getInputStream();
        byte[] bytes = FileCopyUtils.copyToByteArray(inputStream);
        DbConfig dbConfig = new DbConfig();
        dbSearcher = new DbSearcher(dbConfig, bytes);
        method = dbSearcher.getClass().getMethod("memorySearch", String.class);
    }

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

    public static String getUserAgent(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        return UserAgent.parseUserAgentString(userAgent).toString();
    }

    public static String getIpArea(String ipAddress) {
        if (ipAddress == null || !Util.isIpAddress(ipAddress)) {
            logger.error("ip地址为空");
            return "";
        }
        try {
            DataBlock dataBlock = (DataBlock) method.invoke(dbSearcher, ipAddress);
            String ipInfo = dataBlock.getRegion();

            if (!StringUtils.isEmpty(ipInfo)) {
                String[] ips = ipInfo.split("\\|");
                return ips[2];
            }
        } catch (Exception e) {
            logger.error("获取IP归属地失败，{}", e.getMessage());
        }
        return "";
    }
}
