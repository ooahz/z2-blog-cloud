package cn.ahzoo.admin.aop;

import cn.ahzoo.admin.annotation.SystemLogger;
import cn.ahzoo.admin.model.dto.SysLogDTO;
import cn.ahzoo.admin.model.entity.User;
import cn.ahzoo.admin.model.vo.ArticleVO;
import cn.ahzoo.admin.service.SysLogService;
import cn.ahzoo.common.utils.IpUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 日志保存切面
 * @github https://github.com/ooahz
 * @date 2024/5
 */
@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private final HttpServletRequest request;
    private final SysLogService sysLogService;

    @Pointcut(value = "@annotation(systemLogger)")
    public void pointcut(SystemLogger systemLogger) {
    }

    @Around(value = "pointcut(systemLogger)", argNames = "joinPoint,systemLogger")
    @Transactional(rollbackFor = Exception.class)
    public Object doAround(ProceedingJoinPoint joinPoint, SystemLogger systemLogger) throws Throwable {
        Object result = joinPoint.proceed();
        saveLog(joinPoint, systemLogger);
        return result;
    }

    public void saveLog(JoinPoint joinPoint, SystemLogger systemLogger) {
        SysLogDTO log = SysLogDTO.builder().build();
        try {
            String param = buildParams(joinPoint, systemLogger);
            Long loginId = StpUtil.getLoginId(0L);
            String type = request.getMethod();
            String requestUrl = request.getHeader("Origin");
            String ip = IpUtil.getIpAddress(request);
            String methodName = joinPoint.getSignature().getName();
            log = SysLogDTO.builder()
                    .content(systemLogger.value())
                    .methodName(methodName)
                    .param(param)
                    .requestIp(ip)
                    .requestType(type)
                    .requestUrl(requestUrl)
                    .userId(loginId)
                    .createdTime(LocalDateTime.now())
                    .build();
            sysLogService.saveCache(log);
        } catch (Exception e) {
            logger.error("日志记录出错：{},\n日志对象：{}", e.getMessage(), log.toString());
        }
    }

    private String buildParams(JoinPoint joinPoint, SystemLogger systemLogger) {
        // todo switch替代
        if (StringUtils.equals("false", systemLogger.param())) {
            return "";
        }
        if (StringUtils.equals("article", systemLogger.param())) {
            return getArticleParams(joinPoint);
        }
        if (StringUtils.equals("user", systemLogger.param())) {
            return getUserParams(joinPoint);
        }
        Object[] args = joinPoint.getArgs();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        Map<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < parameterNames.length; i++) {
            paramMap.put(parameterNames[i], args[i]);
        }
        String param = JSON.toJSONString(paramMap);
        if (param.length() > 1000) {
            param = param.substring(0, 1000);
        }
        return param;
    }

    private String getUserParams(JoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();
            String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
            int index = Arrays.binarySearch(parameterNames, "user");
            if (index < 0) {
                return "";
            }
            User user = (User) args[index];
            Map<String, String> paramsMap = Map.of(
                    "email", String.valueOf(user.getEmail()),
                    "name", String.valueOf(user.getName())
            );
            return JSON.toJSONString(paramsMap);
        } catch (Exception e) {
            String methodName = joinPoint.getSignature().getName();
            logger.error("组装用户参数失败，方法名：{}，错误：{}", methodName, e.getMessage());
            return "";
        }
    }

    /**
     * 只记录文章id和标题
     *
     * @param joinPoint
     * @return
     */
    private String getArticleParams(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String[] parameterNames = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        int index = Arrays.binarySearch(parameterNames, "articleVO");
        if (index < 0) {
            return "";
        }
        ArticleVO articleVO = (ArticleVO) args[index];
        Map<String, String> paramsMap = Map.of(
                "id", String.valueOf(articleVO.getId()),
                "title", articleVO.getTitle()
        );
        return JSON.toJSONString(paramsMap);
    }
}
