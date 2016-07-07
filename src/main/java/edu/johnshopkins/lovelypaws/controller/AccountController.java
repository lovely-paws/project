package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import edu.johnshopkins.lovelypaws.entity.Administrator;
import edu.johnshopkins.lovelypaws.entity.EndUser;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(path = {"/", ""})
    public ModelAndView showAccount(HttpServletRequest request, ModelMap modelMap) {
        if(request.getSession().getAttribute("userId") == null) {
            modelMap.addAttribute("message", "You are not logged in.");
            return new ModelAndView("redirect:/session/login");
        } else {
            AbstractUser user = userDao.findById(Long.valueOf(request.getSession().getAttribute("userId").toString()));
            request.setAttribute("user", user);

            if(user instanceof EndUser) {
                return new ModelAndView("/account/user");
            } else if(user instanceof Shelter) {
                return new ModelAndView("/account/shelter");
            } else if(user instanceof Administrator) {
                return new ModelAndView("/account/administrator");
            } else {
                // TODO: Log or handle.
                throw new IllegalArgumentException("Unsupported account type "+user);
            }
        }
    }
}
