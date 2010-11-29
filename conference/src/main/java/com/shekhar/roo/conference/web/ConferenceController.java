package com.shekhar.roo.conference.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.shekhar.roo.conference.domain.Conference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "conferences", formBackingObject = Conference.class)
@RequestMapping("/conferences")
@Controller
public class ConferenceController {
}
