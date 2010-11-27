package com.shekhar.playground.roo.wedding.web;

import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.shekhar.playground.roo.wedding.domain.Rsvp;
import org.springframework.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

@RequestMapping("/publicrsvp/**")
@Controller
@SessionAttributes("rsvp")
public class PublicRsvpController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private transient MailSender mailTemplate;

    @Autowired
    private transient SimpleMailMessage simpleMailMessage;

    @RequestMapping
    public String get(ModelMap modelMap) {
        modelMap.put("rsvp", getRsvp());
        return "publicrsvp";
    }

    private Rsvp getRsvp() {
        String code = null;
        Rsvp rsvp = new Rsvp();
        try {
            code = SecurityContextHolder.getContext().getAuthentication().getName();
            rsvp.setCode(code);
            rsvp = Rsvp.findRsvpsByCodeEquals(code).getSingleResult();
        } catch (DataAccessException e) {
            logger.info("No RSVP found for " + code);
        }
        return rsvp;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String post(@ModelAttribute("rsvp") Rsvp rsvp, ModelMap modelMap) {
        rsvp.setConfirmed(new Date());
        if (rsvp.getId() == null) {
            rsvp.persist();
        } else {
            rsvp.merge();
        }
        if (rsvp.getEmail().length() > 0) {
            logger.info("Sending email to " + rsvp.getEmail());
            sendMessage(rsvp.getEmail(), "Congratulations your rsvp has been accepted");
        }
        modelMap.put("rsvp", rsvp);
        return "thanks";
    }

    public void sendMessage(String mailTo, String message) {
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setText(message);
        mailTemplate.send(simpleMailMessage);
    }
}
