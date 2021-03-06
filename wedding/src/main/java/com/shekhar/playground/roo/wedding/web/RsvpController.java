package com.shekhar.playground.roo.wedding.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.shekhar.playground.roo.wedding.domain.Rsvp;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "rsvps", formBackingObject = Rsvp.class)
@RequestMapping("/rsvps")
@Controller
public class RsvpController {
}
