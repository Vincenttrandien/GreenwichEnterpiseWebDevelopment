package com.greenwich.backend.service.impl;

import com.greenwich.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;


    private NotificationService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }


    public void sendNotification(User user){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Sign Up Success");
        mailMessage.setText("This is an auto message. Do Not Send Back");

//        String path1 = "/home/tran/Downloads/test.txt";
//        String path2 = "/home/tran/Downloads/readme.zip";
//
// Attachment 1
//        FileSystemResource file1 = new FileSystemResource(new File(path1));
//        mailMessage.

// Attachment 2
//FileSystemResource file2 = new FileSystemResource(new File(path2));
//helper.addAttachment("Readme", file2);
//emailSender.send(message);
        javaMailSender.send(mailMessage);
    }

}

