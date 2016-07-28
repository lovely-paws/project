package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@Scope("session")
public class Index {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserInfo userInfo;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView sayHello(ModelMap modelMap) {
        return new ModelAndView("index").addAllObjects(modelMap);
    }
}
