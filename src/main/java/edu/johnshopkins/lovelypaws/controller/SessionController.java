package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.beans.LoginData;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.UserBo;
import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@Scope("session")
public class SessionController {

    @Autowired
    private UserBo userBo;

    @Autowired
    private UserInfo userInfo;

    /** [UNAUTHENTICATED USERS] Directs unauthenticated users to the login form; authenticated users are directed to the home page. */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView login(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() != null) {
            redirectAttributes.addFlashAttribute("message", "You are already logged in.");
            return new ModelAndView("redirect:/");
        } else {
            return new ModelAndView("login").addObject("loginData", new LoginData());
        }
    }

    /** [UNAUTHENTICATED USERS] Performs a login attempt using the information provided in the login form; authenticated users are directed to the home page. */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ModelAndView doLogin(@ModelAttribute("loginData") LoginData loginData, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() != null) {
            redirectAttributes.addFlashAttribute("message", "You are already logged in.");
            return new ModelAndView("redirect:/");
        }

        AbstractUser abstractUser = userBo.login(loginData.getUsername(), loginData.getPassword());
        if(abstractUser != null) {
            userInfo.setUser(abstractUser);
            redirectAttributes.addFlashAttribute("message", "You are now logged in!");
            return new ModelAndView("redirect:/");
        } else {
            userInfo.setUser(null);
            loginData.setPassword(null);
            return new ModelAndView("/login")
                    .addObject("message", "Invalid login.")
                    .addObject(loginData);
        }
    }

    /** [ALL USERS] Terminates the current session. */
    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        userInfo.setUser(null);
        httpServletRequest.getSession().invalidate();
        redirectAttributes.addFlashAttribute("message", "You are now logged out.");
        return new ModelAndView("redirect:/");
    }
}
