package com.ybj.cbt.utils.Email;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl  {

        @Autowired
        public JavaMailSender emailSender;

        public void sendSimpleMessage(String to
            , String subject
            , String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
        }

        @Test
        public void testEmail(){
                EmailServiceImpl emailService=new EmailServiceImpl();
                emailService.sendSimpleMessage("312ybj@Gmail.com",  "qq测试右键", "收到了吗");
        }
}