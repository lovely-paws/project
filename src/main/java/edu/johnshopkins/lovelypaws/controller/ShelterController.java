package edu.johnshopkins.lovelypaws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shelter")
public class ShelterController {

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView list(ModelMap modelMap) {
        modelMap.put("foo", "shelter-list");
        return new ModelAndView("index");
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ModelAndView list(ModelMap modelMap, @PathVariable  int id) {
        modelMap.put("foo", id);
        return new ModelAndView("index");
    }
}
