package com.airlines.lmpairlines.services.impl;

import com.airlines.lmpairlines.services.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to,String body) throws MessagingException {
        MimeMessage message =javaMailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);

        helper.setSubject("Registration");
        helper.setTo(to);
        helper.setText("Registration was successful! Your username is "+body+"!",true);
        javaMailSender.send(message);
    }

}
