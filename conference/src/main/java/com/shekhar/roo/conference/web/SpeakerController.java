package com.shekhar.roo.conference.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.shekhar.roo.conference.domain.Speaker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "speakers", formBackingObject = Speaker.class)
@RequestMapping("/speakers")
@Controller
public class SpeakerController {
}
