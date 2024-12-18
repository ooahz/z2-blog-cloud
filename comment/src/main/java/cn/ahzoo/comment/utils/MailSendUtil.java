package cn.ahzoo.comment.utils;

import cn.hutool.core.lang.Validator;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class MailSendUtil {

    private final JavaMailSender javaMailSender;

    @Value("${author.mail.sender}")
    private String sender;

    @Value("${author.mail.enable}")
    private Boolean enable;

    private static final Logger logger = LoggerFactory.getLogger(MailSendUtil.class);

    public void emailNotification(String email) {
        if (!enable) {
            logger.info("邮件发送已关闭，不发送邮件");
            return;
        }
        if (!Validator.isEmail(email)) {
            logger.error("邮箱格式不正确：{}", email);
            return;
        }
        try {
            String content = buildDefaultContent();
            String title = "Z次元-收到了新的回复";
            sendEmail(email, content, title);
        } catch (Exception e) {
            logger.error("发送通知邮件异常，发送邮箱：{}，目标邮箱：{}，message：{}", sender,
                    email, e.getMessage());
        }
    }

    private void sendEmail(String mail, String content, String title) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
        message.setSubject(title);
        message.setFrom(sender);
        message.setTo(mail);
        message.setSentDate(new Date());
        message.setText(content, true);
        javaMailSender.send(mimeMessage);
    }

    /**
     * 自定义邮件样式（支持html）
     *
     * @return 邮件发送的样式
     */
    private static String buildDefaultContent() {
        return "<p>收到了新的评论</p>";
    }
}
