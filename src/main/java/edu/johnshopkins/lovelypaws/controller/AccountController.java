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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
@Scope("session")
public class AccountController {

    @Autowired
    private UserInfo userInfo;

    @RequestMapping(path = {"/", ""})
    public ModelAndView showAccount(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You are not logged in.");
            return new ModelAndView("redirect:/login");
        } else {
            User user = userInfo.getUser();
            switch(user.getRole()) {
                case ADMINISTRATOR:
                    return new ModelAndView("/account/administrator")
                            .addObject("user", user);
                case END_USER:
                    return new ModelAndView("/account/user")
                            .addObject("user", user);
                case SHELTER:
                    return new ModelAndView("/account/shelter")
                            .addObject("user", user);
                default:
                    redirectAttributes.addFlashAttribute("message", "Unknown account type.");
                    return new ModelAndView("redirect:/login");
            }
        }
    }
}
