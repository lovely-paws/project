package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.beans.AdministratorInfo;
import edu.johnshopkins.lovelypaws.beans.EndUserInfo;
import edu.johnshopkins.lovelypaws.beans.ShelterInfo;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.UserBo;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import edu.johnshopkins.lovelypaws.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@Scope("session")
public class UserController {

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private UserBo userBo;

    @Autowired
    private UserDao userDao;

    @RequestMapping(path = {"/", ""})
    public ModelAndView viewAll(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null || userInfo.getUser().getRole() != Role.ADMINISTRATOR) {
            redirectAttributes.addFlashAttribute("message", "You are not authorized to do that.");
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("/user/view-all")
                .addObject("users", userDao.findAll());
    }

    @RequestMapping(path = "/edit/{id}")
    public ModelAndView edit(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to do that.");
            return new ModelAndView("redirect:/");
        } else if(userInfo.getUser().getRole() != Role.ADMINISTRATOR) {
            redirectAttributes.addFlashAttribute("message", "You must be an administrator in to do that.");
            return new ModelAndView("redirect:/");
        }

        User user = userDao.findById(id);
        if(user == null) {
            redirectAttributes.addFlashAttribute("message", "No such user.");
            return new ModelAndView("redirect:/");
        }


        switch(user.getRole()) {
            case ADMINISTRATOR:
                return new ModelAndView("/account/administrator/edit")
                        .addObject("accountInfo", new AdministratorInfo(user));
            case SHELTER:
                return new ModelAndView("/account/shelter/edit")
                        .addObject("accountInfo", new ShelterInfo(user));
            case END_USER:
                return new ModelAndView("/account/end-user/edit")
                        .addObject("accountInfo", new EndUserInfo(user));
            default:
                redirectAttributes.addFlashAttribute("message", "Unknown account type.");
                return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping(path = "/delete/{id}")
    public ModelAndView delete(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to do that.");
            return new ModelAndView("redirect:/");
        } else if(userInfo.getUser().getRole() != Role.ADMINISTRATOR) {
            redirectAttributes.addFlashAttribute("message", "You must be an administrator in to do that.");
            return new ModelAndView("redirect:/");
        }

        User user = userDao.findById(id);
        if(user == null) {
            redirectAttributes.addFlashAttribute("message", "No such user.");
            return new ModelAndView("redirect:/");
        }

        userDao.delete(user.getId());
        return new ModelAndView("redirect:/user");
    }
}
