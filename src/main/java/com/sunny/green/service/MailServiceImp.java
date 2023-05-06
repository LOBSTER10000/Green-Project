//package com.sunny.green.service;
//
//import com.sunny.green.vo.MailVo;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//@Service
//@RequiredArgsConstructor
//@Configuration
//public class MailServiceImp implements MailService{
//
//    private final JavaMailSender javaMailSender;
//
//
//    public void successMail(MailVo mailVo) throws MessagingException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//        mimeMessageHelper.setTo(mailVo.getMail_receiver());
//        mimeMessageHelper.setSubject(mailVo.getMail_title());
//        mimeMessageHelper.setText(mailVo.getMail_content());
//        mimeMessageHelper.setFrom("ghh607@naver.com");
//        javaMailSender.send(mimeMessage);
//
//    }
//}
