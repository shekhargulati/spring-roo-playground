package com.springsource.vote.security;

import org.springframework.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

public class JmsSecurityTopicListener {

    @Autowired
    private transient MailSender mailTemplate;

    @Autowired
    private transient SimpleMailMessage simpleMailMessage;

    public void onMessage(Long timeStamp) {
        System.out.println("JMS security alert event received: " + timeStamp);
        sendMessage("shekhargulati84@gmail.com", "Possible illegal login detected at " + timeStamp);
    }

    public void sendMessage(String mailTo, String message) {
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setText(message);
        mailTemplate.send(simpleMailMessage);
    }
}
