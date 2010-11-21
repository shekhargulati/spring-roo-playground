package com.shekhar.playground.roo.poll.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.shekhar.playground.roo.poll.domain.Answer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "answers", formBackingObject = Answer.class)
@RequestMapping("/answers")
@Controller
public class AnswerController {
}
