package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.beans.CreateUserRequest;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.EndUserBo;
import edu.johnshopkins.lovelypaws.entity.EndUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
@Scope("session")
public class EndUserController {

    @Autowired
    private EndUserBo endUserBo;

    @Autowired
    private UserInfo userInfo;

    @RequestMapping(path = "/register")
    public ModelAndView register() {
        if(userInfo.getUser() == null) {
            return new ModelAndView("/user/register")
                    .addObject("createUserRequest", new CreateUserRequest());
        } else {
            return new ModelAndView("/account")
                    .addObject("message", "You cannot register as a user while logged in to the application.");
        }
    }

    @RequestMapping(path = "/create")
    public ModelAndView createUser(@ModelAttribute("createUserRequest")CreateUserRequest createUserRequest, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() != null) {
            redirectAttributes.addFlashAttribute("message", "You cannot register as a user while logged in to the application.");
            return new ModelAndView("redirect:/index");
        }

        try {
            EndUser endUser = endUserBo.create(createUserRequest);
            userInfo.setUser(endUser);
            redirectAttributes.addFlashAttribute("message", "Account created!");
            return new ModelAndView("redirect:/");
        } catch(IllegalArgumentException illegalArgumentException) {
            // The user provided a bad parameter. Kick them back
            // and have them resubmit the form.
            return new ModelAndView("/user/register")
                    .addObject("createUserRequest", createUserRequest)
                    .addObject("message", illegalArgumentException.getMessage());
        }
    }
}
