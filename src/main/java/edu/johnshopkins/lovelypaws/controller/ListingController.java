package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.beans.ListingSearch;
import edu.johnshopkins.lovelypaws.bo.ListingBo;
import edu.johnshopkins.lovelypaws.dao.AnimalTypeDao;
import edu.johnshopkins.lovelypaws.dao.ListingDao;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/listing")
@SessionAttributes("userId")
public class ListingController {

    @Autowired
    private ListingBo listingBo;

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private AnimalTypeDao animalTypeDao;

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
            Listing listing = listingBo.createListing((Shelter)(abstractUser), animalTypeDao.findByName(requestParams.get("animalType")), requestParams.get("name"), requestParams.get("description"),
                    requestParams.get("color"));
        }
        return new ModelAndView("redirect:/listing/");
    }

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView viewAll(@ModelAttribute ListingSearch listingSearch, ModelMap modelMap) {
        List<Listing> matchedListings = new ArrayList<>();
        long animalTypeId, shelterId;
        for(Listing listing : listingDao.findAll()) {
            animalTypeId = listing.getAnimalType().getId();
            shelterId = listing.getShelter().getId();
            if((listingSearch.getAnimalTypeIds().size() == 0 || listingSearch.getAnimalTypeIds().contains(animalTypeId))
                    && (listingSearch.getShelterIds().size() == 0 || listingSearch.getShelterIds().contains(shelterId))
                    && (listingSearch.getColor() == null || listing.getColor().equals(listingSearch.getColor()))) {
                matchedListings.add(listing);
            }
        }
        modelMap.addAttribute("listings", matchedListings);
        return new ModelAndView("/listing/view-all", modelMap);
    }

    @RequestMapping(path = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(ModelMap modelMap, @SessionAttribute String userId, @PathVariable Long id) {
        if(id == null) {
            return new ModelAndView("redirect:/listing/");
        }

        Listing listing = listingDao.findById(id);
        if(listing == null) {
            return new ModelAndView("redirect:/listing/");
        }

        modelMap.put("listing", listing);
        if(!StringUtils.isBlank(userId)) {
            AbstractUser abstractUser = userDao.findById(Long.valueOf(userId));
            if(abstractUser instanceof EndUser) {
                modelMap.put("canAdopt", true);
            }
        }

        return new ModelAndView("/listing/view", modelMap);
    }
}
