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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /** [SHELTERS] Directs shelters to the create-a-listing form; other users are redirected home. */
    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public ModelAndView create(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to do that.");
            return new ModelAndView("redirect:/");
        } else if(userInfo.getUser().getRole() != Role.SHELTER) {
            redirectAttributes.addFlashAttribute("message", "Your account is not permitted to perform that action.");
            return new ModelAndView("redirect:/");
        } else if(!((Shelter)userInfo.getUser()).isApproved()) {
            redirectAttributes.addFlashAttribute("message", "Your account has not been approved to create listings.");
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("/listing/new-listing")
                .addObject("createListingRequest", new CreateListingRequest())
                .addObject("animalTypes", animalTypeDao.findAll());
    }

    /** [SHELTERS] Attempts to create a listing using the information provided in the creation form. */
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ModelAndView executeCreate(@ModelAttribute("createListingRequest") CreateListingRequest createListingRequest, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to do that.");
            return new ModelAndView("redirect:/");
        } else if(userInfo.getUser().getRole() != Role.SHELTER) {
            redirectAttributes.addFlashAttribute("message", "Your account is not permitted to perform that action.");
            return new ModelAndView("redirect:/");
        } else if(!((Shelter)userInfo.getUser()).isApproved()) {
            redirectAttributes.addFlashAttribute("message", "Your account has not been approved to create listings.");
            return new ModelAndView("redirect:/");
        }

        Listing listing = listingBo.createListing((Shelter)(userInfo.getUser()),
                animalTypeDao.findById(createListingRequest.getAnimalTypeId()),
                createListingRequest.getName(),
                createListingRequest.getDescription(),
                createListingRequest.getColor());

        return new ModelAndView("redirect:/listing/view/"+listing.getId());
    }

    /** [ALL USERS] Displays all of the listings matching the provided listing criteria. */
    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView viewAll(@ModelAttribute(name = "listingSearch") ListingSearch listingSearch, ModelMap modelMap) {
        if(listingSearch == null) {
            listingSearch = new ListingSearch();
        }

        modelMap.put("shelters", shelterDao.findAll());
        modelMap.put("animalTypes", animalTypeDao.findAll());
        modelMap.put("listingSearch", listingSearch);

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
    public ModelAndView view(@PathVariable Long id) {
        if(id == null) {
            return new ModelAndView("redirect:/listing/");
        }

        Listing listing = listingDao.findById(id);
        if(listing == null) {
            return new ModelAndView("redirect:/listing/");
        }

        return new ModelAndView("/listing/view")
                .addObject("listing", listing);
    }
}
