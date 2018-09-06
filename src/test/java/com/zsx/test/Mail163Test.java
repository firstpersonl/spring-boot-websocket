package com.zsx.test;

import com.order_porint.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class Mail163Test {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String username;
    @Test
    public void testSendSimple() {
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message;
        try {
            message = new MimeMessageHelper(mimeMessage, false /* multipart */, "UTF-8");
            message.setSubject("test");
            message.setFrom(username);
            message.setTo("936336472@qq.com");
            // Create the HTML body using Thymeleaf
            message.setText("tesatt", false /* isHtml */);
            this.javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // Create the HTML body using Thymeleaf

        System.out.print("success send");
    }
}
