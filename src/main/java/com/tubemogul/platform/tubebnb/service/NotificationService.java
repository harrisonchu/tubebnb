package com.tubemogul.platform.tubebnb.service;

import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by harrison.chu on 8/6/15.
 */
public class NotificationService {

    @Value("${notification.service.user}")
    private String senderUsername = null;

    @Value("${notification.service.password}")
    private String senderPassword = null;
    Properties props;

    public NotificationService() {
        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }

    public void sendMessage(String recipient, String subject, String messageBody) {
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderUsername, senderPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderUsername));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(messageBody);
            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        service.setSenderUsername("airtube.butler@gmail.com");
        service.setSenderPassword("m0gultuber");

        service.sendMessage("harrison.c.chu@gmail.com", "test", "youwin");
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }
}
