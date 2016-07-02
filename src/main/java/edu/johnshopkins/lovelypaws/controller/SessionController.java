package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.bo.UserBo;
import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private UserBo userBo;

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView viewAll(HttpServletRequest request, ModelMap modelMap) {
        if(request.getSession().getAttribute("userId") != null) {
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("session/login");
        }
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request,
                                @RequestParam("username") String username,
                                @RequestParam("password") String password) {
        if(request.getSession().getAttribute("userId") == null) {
            AbstractUser abstractUser = userBo.login(username, password);
            if(abstractUser != null) {
                System.out.printf("[User %d] Login successful.%n", abstractUser.getId());
                request.getSession(true).setAttribute("userId", abstractUser.getId());
                return new ModelAndView("redirect:/");
            } else {
                return new ModelAndView("redirect:/session/login");
            }
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest httpServletRequest) {
        if(httpServletRequest.getSession().getAttribute("userId") != null) {
            httpServletRequest.getSession().invalidate();
        }
        return new ModelAndView("redirect:/");
    }
}
