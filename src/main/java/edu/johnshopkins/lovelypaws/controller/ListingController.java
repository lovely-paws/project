package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.bo.ListingBo;
import edu.johnshopkins.lovelypaws.bo.ShelterBo;
import edu.johnshopkins.lovelypaws.bo.UserBo;
import edu.johnshopkins.lovelypaws.dao.ListingDao;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.*;
import edu.johnshopkins.lovelypaws.entity.enums.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/listing")
public class ListingController {

    @Autowired
    private ShelterBo shelterBo;

    @Autowired
    private ListingBo listingBo;

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public ModelAndView create(HttpServletRequest httpServletRequest, ModelMap modelMap) {
        if(httpServletRequest.getSession().getAttribute("userId") != null) {
            AbstractUser abstractUser = userDao.findById(Long.valueOf(httpServletRequest.getSession().getAttribute("userId").toString()));
            if(abstractUser instanceof Administrator || abstractUser instanceof Shelter) {
                modelMap.addAttribute("user", abstractUser);
                return new ModelAndView("/activity/new-listing", modelMap);
            }
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ModelAndView executeCreate(HttpServletRequest httpServletRequest, @RequestParam Map<String, String> requestParams, ModelMap modelMap) {
        if(httpServletRequest.getSession().getAttribute("userId") != null) {
            AbstractUser abstractUser = userDao.findById(Long.valueOf(httpServletRequest.getSession().getAttribute("userId").toString()));
            Listing listing = listingBo.createListing((Shelter)(abstractUser), null, requestParams.get("name"), requestParams.get("description"), Color.valueOf(requestParams.get("color")));
        }
        return new ModelAndView("redirect:/listing/");
    }

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView viewAll(ModelMap modelMap) {
        modelMap.addAttribute("listings", listingDao.findAll());
        return new ModelAndView("/listing/view-all", modelMap);
    }
}
