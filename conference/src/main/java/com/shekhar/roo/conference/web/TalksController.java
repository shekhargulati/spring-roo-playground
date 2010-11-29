package com.shekhar.roo.conference.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.shekhar.roo.conference.domain.Talks;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "talkses", formBackingObject = Talks.class)
@RequestMapping("/talkses")
@Controller
public class TalksController {
}
