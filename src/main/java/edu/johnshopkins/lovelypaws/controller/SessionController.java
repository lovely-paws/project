package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.UserBo;
import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class SessionController {

    @Autowired
    private UserBo userBo;

    @Autowired
    private UserInfo userInfo;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView viewAll(HttpServletRequest request, ModelMap modelMap) {
        if(userInfo.getUser() != null) {
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("login");
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password) {
        if(userInfo.getUser() == null) {
            AbstractUser abstractUser = userBo.login(username, password);
            if(abstractUser != null) {
                System.out.printf("[User %d] Login successful.%n", abstractUser.getId());
                userInfo.setUser(abstractUser);
                return new ModelAndView("redirect:/");
            } else {
                userInfo.setUser(null);
                return new ModelAndView("redirect:/login");
            }
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest httpServletRequest) {
        userInfo.setUser(null);
        httpServletRequest.getSession().invalidate();
        return new ModelAndView("redirect:/");
    }
}
