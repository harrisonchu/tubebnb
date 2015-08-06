package com.tubemogul.platform.tubebnb.service;

import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;

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

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
    }
}
