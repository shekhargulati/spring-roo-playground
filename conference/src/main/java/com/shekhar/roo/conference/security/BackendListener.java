package com.shekhar.roo.conference.security;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
@Component
public class BackendListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent>{
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private transient JmsTemplate jmsTemplate;

    private long lastFailureEventTime;

    private long timeLastWarningJmsSent;

    public void sendMessage(Object messageObject) {
        jmsTemplate.convertAndSend(messageObject);
    }

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
        logger.info("Bad credential notification received : " + event.getTimestamp());
        if (lastFailureEventTime > new Date().getTime() - (1000 * 5)) {
            // the last failure happened less than 5 seconds ago

            logger.info("the last failure happened less than 5 seconds ago");

            // so we want to send an email out, provided we haven't sent one in
            // the last 5 minutes
            if ((timeLastWarningJmsSent + 1000 * 60 * 5) < new Date().getTime()) {
                logger.info("the last message was less than 5 minutes ago");

                timeLastWarningJmsSent = new Date().getTime();
                // someone illegally tried to login
                logger.info("Failure event detected at " + event.getTimestamp());
                sendMessage(new Long(event.getTimestamp()));
            }
        }
        lastFailureEventTime = event.getTimestamp();
    }
}
