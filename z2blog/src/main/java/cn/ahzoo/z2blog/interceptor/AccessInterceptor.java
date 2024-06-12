package cn.ahzoo.z2blog.interceptor;

import cn.ahzoo.z2blog.service.AccessService;
import cn.ahzoo.common.utils.IpUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @description 访问量处理拦截器
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Component
@AllArgsConstructor
public class AccessInterceptor implements HandlerInterceptor {

    private final AccessService accessService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = IpUtil.getIpAddress(request);
        accessService.cacheWebsitePVAndUV(ipAddress);
        return true;
    }
}
