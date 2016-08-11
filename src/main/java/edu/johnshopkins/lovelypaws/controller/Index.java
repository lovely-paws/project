package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.beans.ContactInfo;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.AbstractUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
@Scope("session")
public class Index {

    @Autowired
    private UserInfo userInfo;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView view() {
        return new ModelAndView("index")
                .addObject("user", userInfo.getUser());
    }
    
    @RequestMapping("contact-us")
    public ModelAndView contact(ContactInfo contactInfo){
        if(contactInfo == null) {
            contactInfo = new ContactInfo();
        }
        return new ModelAndView("contact")
                .addObject("contactInfo", contactInfo);
    }

    @RequestMapping("contact.do")
    public ModelAndView doContact(ContactInfo contactInfo, RedirectAttributes redirectAttributes){
        if(contactInfo == null) {
            // Send them back.
            redirectAttributes.addFlashAttribute("contactInfo", new ContactInfo());
            return new ModelAndView("redirect:/contact-us");
        } else if(StringUtils.indexOf(contactInfo.getSender(), "@") < 1) {
            redirectAttributes.addFlashAttribute("message", "Invalid e-mail address.");
            redirectAttributes.addFlashAttribute("contactInfo", contactInfo);
            return new ModelAndView("redirect:/contact-us");
        } else if(StringUtils.length(contactInfo.getMessage()) <= 10) {
            redirectAttributes.addFlashAttribute("message", "Your message must be at least 10 characters long.");
            redirectAttributes.addFlashAttribute("contactInfo", contactInfo);
            return new ModelAndView("redirect:/contact-us");
        }

        redirectAttributes.addFlashAttribute("message", "E-mail sent!");
        redirectAttributes.addFlashAttribute("contactInfo", new ContactInfo());
        return new ModelAndView("redirect:/contact-us");
    }
    
    @RequestMapping("about")
    public ModelAndView about(){
    	return new ModelAndView("about");
    }
}
