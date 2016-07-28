package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.beans.ApplicationInfo;
import edu.johnshopkins.lovelypaws.beans.Cart;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.AdoptionRequestBo;
import edu.johnshopkins.lovelypaws.dao.ListingDao;
import edu.johnshopkins.lovelypaws.entity.AdoptionRequest;
import edu.johnshopkins.lovelypaws.entity.EndUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
@Scope("session")
public class CartController {

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private Cart cart;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private AdoptionRequestBo adoptionRequestBo;

    @RequestMapping(path = {"/", ""})
    public ModelAndView viewCart(ModelMap modelMap) {
        if(userInfo.getUser() instanceof EndUser) {
            return new ModelAndView("/cart/view")
                    .addAllObjects(modelMap);
        } else {
            ModelAndView modelAndView = new ModelAndView("/index")
                    .addObject("message", "You are not logged in or your account type does not support the shopping cart feature.");
            return modelAndView;
        }
    }

    @RequestMapping(path = "/add")
    public ModelAndView addListing(@RequestParam Long id) {
        if(id != null) {
            cart.getIds().add(id);
        }
        return new ModelAndView("redirect:/cart");
    }

    @RequestMapping(path = {"/checkout"})
    public ModelAndView checkout(ModelMap modelMap) {
        if(!(userInfo.getUser() instanceof EndUser)) {
            return new ModelAndView("redirect:/cart")
                    .addAllObjects(modelMap)
                    .addObject("message", "You cannot checkout - you are not logged in or your accountt type does not support this operation.");
        } else if(cart.getIds().size() == 0) {
            return new ModelAndView("redirect:/cart")
                    .addAllObjects(modelMap)
                    .addObject("message", "You cannot checkout - you have not added any listings.");
        }
        return new ModelAndView("cart/checkout")
                .addAllObjects(modelMap)
                .addObject("listings", listingDao.findByIds(cart.getIds()));
    }

    @RequestMapping(path = {"/apply"})
    public ModelAndView apply(ModelMap modelMap, @ModelAttribute("application")ApplicationInfo applicationInfo) {
        if(!(userInfo.getUser() instanceof EndUser)) {
            return new ModelAndView("redirect:/cart")
                    .addAllObjects(modelMap)
                    .addObject("message", "You cannot checkout - you are not logged in or your accountt type does not support this operation.");
        } else if(cart.getIds().size() == 0) {
            return new ModelAndView("redirect:/cart")
                    .addAllObjects(modelMap)
                    .addObject("message", "You cannot checkout - you have not added any listings.");
        } else if(!applicationInfo.isAccepted()) {
            return new ModelAndView("redirect:/cart/checkout")
                    .addAllObjects(modelMap)
                    .addObject("message", "You cannot checkout - you must accept the terms of use.");
        } else {
            adoptionRequestBo.create(applicationInfo, (EndUser)userInfo.getUser(), cart.getIds());

            cart.getIds().clear();
            return new ModelAndView("redirect:/")
                    .addAllObjects(modelMap)
                    .addObject("message", "Your application has been submitted!");
        }
    }
}
