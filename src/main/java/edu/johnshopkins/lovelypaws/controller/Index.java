package edu.johnshopkins.lovelypaws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class Index {
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView sayHello(ModelMap model) {
        model.addAttribute("foo", "bar");
        return new ModelAndView("index");
    }
}
