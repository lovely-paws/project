package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.Age;
import edu.johnshopkins.lovelypaws.Gender;
import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.beans.ListingInfo;
import edu.johnshopkins.lovelypaws.beans.ListingSearch;
import edu.johnshopkins.lovelypaws.beans.ServerResponse;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.AdoptionRequestBo;
import edu.johnshopkins.lovelypaws.bo.ListingBo;
import edu.johnshopkins.lovelypaws.dao.AnimalTypeDao;
import edu.johnshopkins.lovelypaws.dao.ListingDao;
import edu.johnshopkins.lovelypaws.dao.ShelterHibernateDao;
import edu.johnshopkins.lovelypaws.dao.UserDao;
import edu.johnshopkins.lovelypaws.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                .addObject("listingInfo", new ListingInfo())
                .addObject("animalTypes", animalTypeDao.findAll())
                .addObject("ages", Arrays.asList(Age.values()))
                .addObject("genders", Arrays.asList(Gender.values()));
    }

    /** [SHELTERS] Attempts to create a listing using the information provided in the creation form. */
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ModelAndView executeCreate(ListingInfo listingInfo, RedirectAttributes redirectAttributes) {
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
                animalTypeDao.findById(listingInfo.getAnimalTypeId()),
                listingInfo.getName(),
                listingInfo.getDescription(),
                listingInfo.getColor(),
                listingInfo.getGender(),
                listingInfo.getAge());

        return new ModelAndView("redirect:/listing/view/"+listing.getId());
    }

    @RequestMapping(path = "/edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        Listing listing = listingDao.findById(id);
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to do that.");
            return new ModelAndView("redirect:/");
        } else if(listing == null) {
            redirectAttributes.addFlashAttribute("message", "No such listing.");
            return new ModelAndView("redirect:/");
        } else if(listing.getShelter().getId() != userInfo.getUser().getId() && userInfo.getUser().getRole() != Role.ADMINISTRATOR) {
            redirectAttributes.addFlashAttribute("message", "You must own the listing or be an administrator to edit it.");
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("/listing/edit")
                .addObject("listingInfo", new ListingInfo(listing))
                .addObject("animalTypes", animalTypeDao.findAll())
                .addObject("genders", Arrays.asList(Gender.values()))
                .addObject("ages", Arrays.asList(Age.values()));
    }

    @RequestMapping(path = "/edit.do")
    public ModelAndView doEdit(ListingInfo listingInfo, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to do that.");
            return new ModelAndView("redirect:/");
        }

        ServerResponse<Listing> response = listingBo.update(userInfo.getUser(), listingInfo);
        if(response.isSuccess()) {
            redirectAttributes.addFlashAttribute("message", "Listing updated!");
            return new ModelAndView("redirect:/listing/");
        } else {
            redirectAttributes.addFlashAttribute("message", "Updated failed - "+response.getMessage());
            if(listingInfo != null) {
                return new ModelAndView("/listing/edit")
                        .addObject("listingInfo", listingInfo)
                        .addObject("message", "Failed to update - "+response.getMessage())
                        .addObject("animalTypes", animalTypeDao.findAll())
                        .addObject("genders", Arrays.asList(Gender.values()))
                        .addObject("ages", Arrays.asList(Age.values()));
            } else {
                redirectAttributes.addFlashAttribute("message", "Empty response body.");
                return new ModelAndView("redirect:/");
            }
        }
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

