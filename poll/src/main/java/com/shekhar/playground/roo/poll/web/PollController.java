package com.shekhar.playground.roo.poll.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.shekhar.playground.roo.poll.domain.Poll;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "polls", formBackingObject = Poll.class)
@RequestMapping("/polls")
@Controller
public class PollController {
}
