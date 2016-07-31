package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.beans.CreateListingRequest;
import edu.johnshopkins.lovelypaws.beans.ListingSearch;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.AdoptionRequestBo;
import edu.johnshopkins.lovelypaws.bo.ListingBo;
import edu.johnshopkins.lovelypaws.dao.AnimalTypeDao;
import edu.johnshopkins.lovelypaws.dao.ListingDao;
import edu.johnshopkins.lovelypaws.dao.ShelterHibernateDao;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
@Scope("session")
public class ListingController {

    @Autowired
    private ListingBo listingBo;

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private AnimalTypeDao animalTypeDao;

    @Autowired
    private ShelterHibernateDao shelterDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private AdoptionRequestBo adoptionRequestBo;

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public ModelAndView create() {
        if(userInfo.getUser() != null) {
            User user = userInfo.getUser();
            if(user.getRole() == Role.SHELTER) {
                return new ModelAndView("/listing/new-listing")
                        .addObject("createListingRequest", new CreateListingRequest())
                        .addObject("animalTypes", animalTypeDao.findAll());
            }
        }
        return new ModelAndView("redirect:/");
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ModelAndView executeCreate(@ModelAttribute("createListingRequest") CreateListingRequest createListingRequest) {
        if(userInfo.getUser() != null) {
            Listing listing = listingBo.createListing((Shelter)(userInfo.getUser()),
                    animalTypeDao.findById(createListingRequest.getAnimalTypeId()),
                    createListingRequest.getName(),
                    createListingRequest.getDescription(),
                    createListingRequest.getColor());
        }
        return new ModelAndView("redirect:/listing/");
    }

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView viewAll(@ModelAttribute(name = "listingSearch") ListingSearch listingSearch, ModelMap modelMap) {
        if(listingSearch == null) {
            modelMap.put("listingSearch", new ListingSearch());
        }

        modelMap.put("shelters", shelterDao.findAll());
        modelMap.put("animalTypes", animalTypeDao.findAll());

        List<Listing> matchedListings = new ArrayList<>();
        long animalTypeId, shelterId;
        for(Listing listing : listingDao.findAll()) {
            animalTypeId = listing.getAnimalType().getId();
            shelterId = listing.getShelter().getId();
            if((listingSearch.getShelterId() == null || listingSearch.getShelterId() == shelterId) &&
                    (listingSearch.getAnimalTypeId() == null || listingSearch.getAnimalTypeId() == animalTypeId)) {
                matchedListings.add(listing);
            }
        }
        modelMap.addAttribute("listings", matchedListings);
        return new ModelAndView("/listing/view-all", modelMap);
    }

    @RequestMapping(path = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(ModelMap modelMap, @SessionAttribute UserInfo userInfo, @PathVariable Long id) {
        if(id == null) {
            return new ModelAndView("redirect:/listing/");
        }

        Listing listing = listingDao.findById(id);
        if(listing == null) {
            return new ModelAndView("redirect:/listing/");
        }

        modelMap.put("listing", listing);
        if(userInfo.getUser() != null && userInfo.getUser() instanceof EndUser) {
            modelMap.put("canAdopt", true);
        }

        return new ModelAndView("/listing/view", modelMap);
    }
}
