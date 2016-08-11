package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.LovelyPawsConstants;
import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.beans.ApplicationInfo;
import edu.johnshopkins.lovelypaws.beans.Cart;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.AdoptionRequestBo;
import edu.johnshopkins.lovelypaws.bo.Mailer;
import edu.johnshopkins.lovelypaws.dao.ListingDao;
import edu.johnshopkins.lovelypaws.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/cart")
@Scope("session")
public class CartController {
    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @Value("#{servletContext.contextPath}")
    private String servletContextPath;

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private Cart cart;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private AdoptionRequestBo adoptionRequestBo;

    @RequestMapping(path = {"/", ""})
    public ModelAndView viewCart(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to do that.");
            return new ModelAndView("redirect:/");
        } else if(userInfo.getUser().getRole() != Role.END_USER) {
            redirectAttributes.addFlashAttribute("message", "Your account type does not support the shopping cart feature.");
            return new ModelAndView("/index");
        } else {
            List<Listing> listings = listingDao.findByIds(cart.getIds());
            return new ModelAndView("/cart/view")
                    .addObject("listings", listings);
        }
    }

    @RequestMapping(path = "/add/{id}")
    public ModelAndView addListing(@PathVariable Long id) {
        if(id != null) {
            cart.getIds().add(id);
        }
        return new ModelAndView("redirect:/cart");
    }

    @RequestMapping(path = "/remove/{id}")
    public ModelAndView removeListing(@PathVariable Long id) {
        if(id != null) {
            cart.getIds().remove(id);
        }
        return new ModelAndView("redirect:/cart");
    }

    @RequestMapping(path = {"/checkout"})
    public ModelAndView checkout(RedirectAttributes redirectAttributes) {
        if(!(userInfo.getUser() instanceof EndUser)) {
            redirectAttributes.addFlashAttribute("message", "You cannot checkout - you are not logged in or your accountt type does not support this operation.");
            return new ModelAndView("redirect:/cart");
        } else if(cart.getIds().size() == 0) {
            redirectAttributes.addFlashAttribute("message", "You cannot checkout - you have not added any listings.");
            return new ModelAndView("redirect:/cart");
        }
        return new ModelAndView("/cart/checkout")
                .addObject("applicationInfo", new ApplicationInfo())
                .addObject("listings", listingDao.findByIds(cart.getIds()));
    }

    @RequestMapping(path = {"/apply"})
    public ModelAndView apply(@ModelAttribute("applicationInfo")ApplicationInfo applicationInfo, RedirectAttributes redirectAttributes) {
        if(!(userInfo.getUser() instanceof EndUser)) {
            redirectAttributes.addFlashAttribute("message", "You cannot checkout - you are not logged in or your account type does not support this operation.");
            return new ModelAndView("redirect:/cart");
        } else if(cart.getIds().size() == 0) {
            redirectAttributes.addFlashAttribute("message", "You cannot checkout - you have not added any listings.");
            return new ModelAndView("redirect:/cart");
        } else if(!applicationInfo.isAccepted()) {
            redirectAttributes.addFlashAttribute("message", "You cannot checkout - you must accept the terms of use.");
            redirectAttributes.addFlashAttribute("applicationInfo", applicationInfo);
            return new ModelAndView("redirect:/cart/checkout");
        } else {
            adoptionRequestBo.create(applicationInfo, (EndUser)userInfo.getUser(), cart.getIds());
            Set<String> shelterAddresses = new HashSet<>();
            List<Listing> listings = listingDao.findByIds(cart.getIds());
            for(Listing listing : listings) {
                if(listing != null) {
                    shelterAddresses.add(listing.getShelter().getEmailAddress());
                }
            }
            cart.getIds().clear();
            for(String shelterAddress : shelterAddresses) {
                try {
                    Mailer.send(shelterAddress,
                            LovelyPawsConstants.EMAIL_ADDRESS,
                            "New Adoption Request",
                            createAdoptionRequestMessage((EndUser)(userInfo.getUser()), applicationInfo));
                } catch(Exception exception) {
                    log.error("Failed to send a notification to {}",
                            shelterAddress, exception);
                }
            }

            redirectAttributes.addFlashAttribute("message", "Your application has been submitted!");
            return new ModelAndView("redirect:/");
        }
    }

    private String createAdoptionRequestMessage(EndUser user, ApplicationInfo applicationInfo) {
        return new StringBuilder()
                .append(String.format("%s has applied to adopt one or more of the pets ", user.getName()))
                .append(String.format("that you have listed on the Lovely Paws network.<br>"))
                .append(String.format("Please visit the <a href='%s'>Lovely Paws site</a> to review the request and make a determination.",
                        servletContextPath))
                .toString();
    }
}
