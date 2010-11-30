package com.shekhar.roo.conference.security;

import org.springframework.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

public class JmsSecurityTopicListener {

    @Autowired
    private transient MailSender mailTemplate;

    @Autowired
    private transient SimpleMailMessage simpleMailMessage;

    public void onMessage(Long message) {
        System.out.println("JMS message received: " + message);
        sendMessage("shekhargulati84@gmail.com", "Illegal access attempt at "+message);
        
    }

    public void sendMessage(String mailTo, String message) {
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setText(message);
        mailTemplate.send(simpleMailMessage);
    }
}
