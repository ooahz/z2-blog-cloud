package cn.ahzoo.common.constant;

public interface RedisConstant {
    /**
     * 分隔符
     */
    String KEY_SEPARATOR = ":";

    /**
     * 前缀
     */
    String ACCESS_PREFIX = "blog:access:";

    String SYSTEM_LOG_PREFIX = "system:log:";

    /**
     * key
     */
    String SYSTEM_STATISTICS_KEY = "system:statistics";

    String COMMENT_TOP_KEY = "comment:top";

    String BLOG_FRIENDS_KEY = "blog:friends";

    String BLOG_AUTHOR_KEY = "blog:author";

    /**
     * 参数变量
     */
    int DEFAULT_KEY_EXPIRE_HOUR = 30;
}
