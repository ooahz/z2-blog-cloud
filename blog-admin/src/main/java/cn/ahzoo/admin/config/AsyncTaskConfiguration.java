package cn.ahzoo.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.support.TaskExecutorAdapter;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author 十玖八柒（Ahzoo）
 * @description 异步任务启用虚拟线程
 * @github https://github.com/ooahz
 * @date 2024/6
 */
@Configuration
public class AsyncTaskConfiguration implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        return new TaskExecutorAdapter(
                Executors.newThreadPerTaskExecutor(
                        Thread.ofVirtual()
                                .name("z2blog-virtual-async#", 1)
                                .factory()));
    }
}
