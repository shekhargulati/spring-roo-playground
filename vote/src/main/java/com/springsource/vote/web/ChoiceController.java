package com.springsource.vote.web;

import org.springframework.roo.addon.web.mvc.controller.RooWebScaffold;
import com.springsource.vote.domain.Choice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

@RooWebScaffold(path = "choices", formBackingObject = Choice.class)
@RequestMapping("/choices")
@Controller
public class ChoiceController {
}
