// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.springsource.vote.web;

import com.springsource.vote.domain.Choice;
import java.io.UnsupportedEncodingException;
import java.lang.Long;
import java.lang.String;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect ChoiceController_Roo_Controller {
    
    @Autowired
    private GenericConversionService ChoiceController.conversionService;
    
    @RequestMapping(method = RequestMethod.POST)
    public String ChoiceController.create(@Valid Choice choice, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("choice", choice);
            return "choices/create";
        }
        choice.persist();
        return "redirect:/choices/" + encodeUrlPathSegment(choice.getId().toString(), request);
    }
    
    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String ChoiceController.createForm(Model model) {
        model.addAttribute("choice", new Choice());
        return "choices/create";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String ChoiceController.show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("choice", Choice.findChoice(id));
        model.addAttribute("itemId", id);
        return "choices/show";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String ChoiceController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            model.addAttribute("choices", Choice.findChoiceEntries(page == null ? 0 : (page.intValue() - 1) * sizeNo, sizeNo));
            float nrOfPages = (float) Choice.countChoices() / sizeNo;
            model.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            model.addAttribute("choices", Choice.findAllChoices());
        }
        return "choices/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT)
    public String ChoiceController.update(@Valid Choice choice, BindingResult result, Model model, HttpServletRequest request) {
        if (result.hasErrors()) {
            model.addAttribute("choice", choice);
            return "choices/update";
        }
        choice.merge();
        return "redirect:/choices/" + encodeUrlPathSegment(choice.getId().toString(), request);
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String ChoiceController.updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("choice", Choice.findChoice(id));
        return "choices/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String ChoiceController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model model) {
        Choice.findChoice(id).remove();
        model.addAttribute("page", (page == null) ? "1" : page.toString());
        model.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/choices?page=" + ((page == null) ? "1" : page.toString()) + "&size=" + ((size == null) ? "10" : size.toString());
    }
    
    Converter<Choice, String> ChoiceController.getChoiceConverter() {
        return new Converter<Choice, String>() {
            public String convert(Choice choice) {
                return new StringBuilder().append(choice.getNamingChoice()).append(" ").append(choice.getDescription()).toString();
            }
        };
    }
    
    @PostConstruct
    void ChoiceController.registerConverters() {
        conversionService.addConverter(getChoiceConverter());
    }
    
    private String ChoiceController.encodeUrlPathSegment(String pathSegment, HttpServletRequest request) {
        String enc = request.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        }
        catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
