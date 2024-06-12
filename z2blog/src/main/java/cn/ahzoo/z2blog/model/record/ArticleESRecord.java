package cn.ahzoo.z2blog.model.record;

/**
 * @description 接收从ES服务传递的数据
 * @author 十玖八柒（Ahzoo）
 * @github https://github.com/ooahz
 * @date 2024/5
 */
public record ArticleESRecord(String id, Long articleId, String title, String content) {
}
