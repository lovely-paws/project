package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.beans.*;
import edu.johnshopkins.lovelypaws.bo.UserBo;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/account")
@Scope("session")
public class AccountController {

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private UserBo userBo;

    @Autowired
    private UserDao userDao;

    @RequestMapping(path ="/edit.do")
    @Transactional
    public <T extends AccountInfo> ModelAndView doEdit(AccountInfo accountInfo,
                                                       AdministratorInfo administratorInfo,
                                                       ShelterInfo shelterInfo,
                                                       EndUserInfo endUserInfo,
                                                       RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You are not logged in.");
            return new ModelAndView("redirect:/");
        }

        User user = userDao.findById(accountInfo.getId());
        if(user == null) {
            redirectAttributes.addFlashAttribute("message", "Unknown user.");
            return new ModelAndView("redirect:/");
        }

        ServerResponse<User> response;
        switch(user.getRole()) {
            case ADMINISTRATOR:
                response = userBo.update(administratorInfo);
                break;
            case SHELTER:
                response = userBo.update(shelterInfo);
                break;
            case END_USER:
                response = userBo.update(endUserInfo);
                break;
            default:
                response = new ServerResponse<>(false, "Unknown account type.", null);
        }


        if(!response.isSuccess()) {
            if(accountInfo != null) {
                redirectAttributes.addFlashAttribute("message", response.getMessage());
                redirectAttributes.addFlashAttribute("accountInfo", accountInfo);
                return new ModelAndView("redirect:/account/edit");
            } else {
                redirectAttributes.addFlashAttribute("message", "Invalid request.");
                return new ModelAndView("redirect:/");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Account updated!");
            return new ModelAndView("redirect:/account");
        }
    }

    @RequestMapping(path = "/edit")
    public ModelAndView edit(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in.");
            return new ModelAndView("redirect:/");
        }

        switch(userInfo.getUser().getRole()) {
            case ADMINISTRATOR:
                return new ModelAndView("/account/administrator/edit")
                        .addObject("accountInfo", new AdministratorInfo(userInfo.getUser()));
            case SHELTER:
                return new ModelAndView("/account/shelter/edit")
                        .addObject("accountInfo", new ShelterInfo(userInfo.getUser()));
            case END_USER:
                return new ModelAndView("/account/end-user/edit")
                        .addObject("accountInfo", new EndUserInfo(userInfo.getUser()));
            default:
                redirectAttributes.addFlashAttribute("message", "Unknown account type.");
                return new ModelAndView("redirect:/");
        }
    }

    @RequestMapping(path = {"/", ""})
    public ModelAndView view(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You are not logged in.");
            return new ModelAndView("redirect:/");
        }
        userInfo.setUser(userDao.findById(userInfo.getUser().getId())); // Reload the current user info in case it was changed.

        return new ModelAndView("/account/view")
                .addObject("userInfo", userInfo);
    }
}
