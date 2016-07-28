package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
@Scope("session")
public class AccountController {

    @Autowired
    private UserInfo userInfo;

    @RequestMapping(path = {"/", ""})
    public ModelAndView showAccount(HttpServletRequest request, ModelMap modelMap) {
        if(userInfo.getUser() == null) {
            modelMap.addAttribute("message", "You are not logged in.");
            return new ModelAndView("redirect:/login");
        } else {
            User user = userInfo.getUser();
            request.setAttribute("user", user);
            switch(user.getRole()) {
                case ADMINISTRATOR:
                    return new ModelAndView("/account/administrator");
                case END_USER:
                    return new ModelAndView("/account/user");
                case SHELTER:
                    request.setAttribute("shelter", userInfo.getUser());
                    return new ModelAndView("/account/shelter");
                default:
                    return new ModelAndView("redirect:/login");
            }
        }
    }
}
