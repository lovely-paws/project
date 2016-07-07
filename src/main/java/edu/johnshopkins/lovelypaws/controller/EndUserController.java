package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.bo.EndUserBo;
import edu.johnshopkins.lovelypaws.bo.UserBo;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import edu.johnshopkins.lovelypaws.entity.EndUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
@SessionAttributes("userId")
public class EndUserController {

    @Autowired
    private EndUserBo endUserBo;

    @RequestMapping(path = "/register")
    public ModelAndView register(HttpServletRequest request, @ModelAttribute String userId, ModelMap modelMap) {
        if(request.getAttribute("userId") == null) {
            return new ModelAndView("/user/register");
        } else {
            request.setAttribute("message", "You cannot register as a user while logged in to the application.");
            return new ModelAndView("/account", modelMap);
        }
    }

    @RequestMapping(path = "/create")
    public ModelAndView createUser(HttpServletRequest request, @ModelAttribute String userId, @ModelAttribute EndUser endUser) {
        if(StringUtils.isBlank(userId)) {
            endUser = endUserBo.create(endUser);
            request.getSession().setAttribute("userId", endUser.getId());
            request.setAttribute("message", "User created!");
        } else {
            request.setAttribute("message", "You cannot create an account while logged in to the application.");
        }
        return new ModelAndView("redirect:/account");
    }
}
