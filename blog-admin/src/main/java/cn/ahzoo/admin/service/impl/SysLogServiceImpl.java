package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.enums.ResultCode;
import cn.ahzoo.admin.exception.BizException;
import cn.ahzoo.admin.mapper.SysLogMapper;
import cn.ahzoo.admin.model.dto.SysLogDTO;
import cn.ahzoo.admin.model.entity.SysLog;
import cn.ahzoo.admin.service.SysLogService;
import cn.ahzoo.admin.utils.RedisUtil;
import cn.ahzoo.common.constant.RedisConstant;
import cn.ahzoo.utils.utils.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog>
        implements SysLogService {

    private static final Logger logger = LoggerFactory.getLogger(SysLogService.class);

    private final RedisUtil redisUtil;
    private final SqlSessionFactory sqlSessionFactory;

    @Override
    public void saveCache(SysLogDTO log) {
        int day = DateUtil.getDayOfToday();
        String key = RedisConstant.SYSTEM_LOG_PREFIX + day;
        redisUtil.lLeftPush(key, JSON.toJSONString(log));
        setKeyExpire(key);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveLog() {
        int yesterday = DateUtil.getDayOfYesterday();
        logger.info("开始同步日志，日期：{}", yesterday);
        Long count = redisUtil.lLen(RedisConstant.SYSTEM_LOG_PREFIX + yesterday);
        if (count <= 0) {
            logger.info("日志数据为空：{}", count);
            return;
        }
        try (SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false)) {
            // 每次从列表中取100条数据提交一次
            for (int i = 0; i < count; i += 100) {
                int end = i + 100 < count ? i + 100 : (int) (count - 1);
                List<String> logs = redisUtil.lRange(RedisConstant.SYSTEM_LOG_PREFIX + yesterday, i, end);
                List<SysLog> sysLogList = JSONArray.parseArray(logs.toString(), SysLog.class);
                sysLogList.forEach(sysLog -> {
                    session.getMapper(SysLogMapper.class)
                            .insert(sysLog);
                });
                session.flushStatements();
                session.commit();
                session.clearCache();
            }
        } catch (Exception e) {
            logger.error("日志同步失败：{}", e.getMessage());
            throw new BizException(ResultCode.EXECUTION_ERROR.getCode(), e.getMessage());
        }
    }

    private void setKeyExpire(String key) {
        Long expire = redisUtil.getExpire(key);
        // 小于0表示永不过期
        if (expire < 0) {
            redisUtil.expire(key, RedisConstant.DEFAULT_KEY_EXPIRE_HOUR, TimeUnit.HOURS);
        }
    }
}




