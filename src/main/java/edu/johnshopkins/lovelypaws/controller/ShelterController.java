package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.AdoptionRequestResult;
import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.beans.ShelterData;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.AdoptionRequestBo;
import edu.johnshopkins.lovelypaws.dao.AdoptionRequestDao;
import edu.johnshopkins.lovelypaws.entity.*;
import edu.johnshopkins.lovelypaws.bo.ShelterBo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.function.Predicate;

@Controller
@RequestMapping("/shelter")
@Scope("session")
public class ShelterController {

    @Autowired
    private ShelterBo shelterBo;

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private AdoptionRequestBo adoptionRequestBo;

    @Autowired
    private AdoptionRequestDao adoptionRequestDao;

    /** [ALL USERS] Provides a listing of shelters registered with the system. */
    @RequestMapping(path = {"", "/"})
    public ModelAndView viewAll() {
        return new ModelAndView("shelter/view-all")
                .addObject("shelters", shelterBo.getAllShelters());
    }

    /**
     * [UNAUTHENTICATED USERS] Directs unauthenticated users to the registration form for servers; authenticated
     * users are returned back to the main page.
     */
    @RequestMapping(path = {"/register"})
    public ModelAndView register(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            return new ModelAndView("shelter/register")
                    .addObject("shelterData", new ShelterData());
        } else {
            redirectAttributes.addFlashAttribute("message", "You must log out in order to register.");
            return new ModelAndView("redirect:/");
        }
    }

    /**
     * [SHELTERS] Displays a listing of pending adoption requests associated with this shelter; users
     * with an incompatible role are redirected to the main page.
     */
    @RequestMapping(path = "/requests")
    public ModelAndView requests(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            // User is not logged in.
            redirectAttributes.addFlashAttribute("message", "You are not logged in.");
            return new ModelAndView("redirect:/");
        } else if(!(userInfo.getUser() instanceof Shelter)) {
            // User is not logged in.
            redirectAttributes.addFlashAttribute("message", "Your account does not support that action.");
            return new ModelAndView("redirect:/");
        }

        Collection<AdoptionRequest> requests = shelterBo.getAdoptionRequests((Shelter)userInfo.getUser());
        requests.removeIf(new Predicate<AdoptionRequest>() {
            @Override
            public boolean test(AdoptionRequest adoptionRequest) {
                return adoptionRequest.getAdoptionRequestResult() != AdoptionRequestResult.PENDING;
            }
        });

        return new ModelAndView("shelter/requests")
                .addObject("requests", requests);
    }

    /**
     * [ADMINISTRATORS] Displays a listing of pending adoption requests associated with the provided shelter;
     * users with an incompatible role are redirected to the main page.
     */
    @RequestMapping(path = "/requests/{id}")
    public ModelAndView requestsForShelter(@PathVariable("id") long id, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            // User is not logged in.
            redirectAttributes.addFlashAttribute("message", "You are not logged in.");
            return new ModelAndView("redirect:/");
        } else if(!(userInfo.getUser() instanceof Administrator) && userInfo.getUser().getId() != id) {
            // User is not logged in.
            redirectAttributes.addFlashAttribute("message", "Your account does not support that action.");
            return new ModelAndView("redirect:/");
        }

        Collection<AdoptionRequest> requests = shelterBo.getAdoptionRequests(shelterBo.getShelter(id));
        requests.removeIf(new Predicate<AdoptionRequest>() {
            @Override
            public boolean test(AdoptionRequest adoptionRequest) {
                return adoptionRequest.getAdoptionRequestResult() != AdoptionRequestResult.PENDING;
            }
        });

        return new ModelAndView("shelter/requests")
                .addObject("requests", requests);
    }

    /**
     * [UNAUTHENTICATED USERS] Attempts to create a new shelter based on the information submitted in a form;
     * authenticated users are returned to the home page.
     */
    @RequestMapping(path = "/create")
    public ModelAndView createShelter(@ModelAttribute("shelterData") ShelterData shelterData, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() != null) {
            redirectAttributes.addFlashAttribute("message", "You cannot register as a shelter while logged in.");
            return new ModelAndView("redirect:/");
        }

        try {
            Shelter shelter = shelterBo.createShelter(shelterData.getUsername(),
                    DigestUtils.sha512Hex(shelterData.getPasswordSha512()),
                    shelterData.getName(), shelterData.getDescription(), shelterData.getAddressData(),
                    shelterData.getPhoneNumber(), shelterData.getEmailAddress(), new ArrayList<>());
            userInfo.setUser(shelter);
            redirectAttributes.addFlashAttribute("message", "Account created - you are now logged in!");
            return new ModelAndView("redirect:/");
        } catch (Exception exception) {
            return new ModelAndView("/shelter/register")
                    .addObject("message", String.format("Could not create this shelter: %s", exception.getMessage()));
        }
    }

    /** [ALL USERS] Displays specific information about a single shelter. If no such shelter exists, redirect to the main page. */
    @RequestMapping(path = "/view/{id}")
    public ModelAndView viewShelter(@PathVariable long id, RedirectAttributes redirectAttributes) {
        Shelter shelter = shelterBo.getShelter(id);
        if(shelter == null) {
            redirectAttributes.addFlashAttribute("message", "A shelter with the provided ID does not exist.");
            return new ModelAndView("redirect:/");
        }
        return new ModelAndView("shelter/view")
                .addObject("shelter", shelter);
    }

    /**
     * [SHELTERS AND ADMINISTRATORS] Approves or rejects an adoption request; if the request was made by a shelter which
     * does not own the request, no action is taken.
     */
    @RequestMapping(path = "/close/{id}")
    public ModelAndView close(@PathVariable Long id, @RequestParam(name = "result") Boolean result, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null) {
            redirectAttributes.addFlashAttribute("message", "You must be logged in to perform that action.");
            return new ModelAndView("redirect:/");
        } else if(userInfo.getUser().getRole() == Role.END_USER || userInfo.getUser().getRole() == Role.UNKNOWN) {
            redirectAttributes.addFlashAttribute("message", "Your role does not support that action.");
            return new ModelAndView("redirect:/");
        }

        AdoptionRequest adoptionRequest = adoptionRequestDao.findById(id);
        if(adoptionRequest == null) {
            redirectAttributes.addFlashAttribute("message", "No matching adoption request was found.");
            return new ModelAndView("redirect:/shelter");
        }

        boolean isMatchingShelter = (userInfo.getUser().getId() == adoptionRequest.getListing().getShelter().getId());
        if(userInfo.getUser().getRole() != Role.ADMINISTRATOR && !isMatchingShelter) {
            redirectAttributes.addFlashAttribute("message", "You are not permitted to approve or reject this adoption request.");
            return new ModelAndView("redirect:/shelter/requests/"+adoptionRequest.getListing().getShelter().getId());
        } else {
            adoptionRequestBo.close(id, result);
            redirectAttributes.addFlashAttribute("message", "Adoption request closed.");
            return new ModelAndView("redirect:/");
        }
    }

    /** [ADMINISTRATORS] Approves a shelter to create new listings. */
    @RequestMapping(path = "/approve/{id}")
    public ModelAndView approveShelter(@PathVariable long id, RedirectAttributes redirectAttributes) {
        return updateShelterApprovedStatus(id, redirectAttributes, true);
    }

    /** [ADMINISTRATORS] Denies a shelter the ability to create new listings. */
    @RequestMapping(path = "/deny/{id}")
    public ModelAndView denyShelter(@PathVariable long id, RedirectAttributes redirectAttributes) {
        return updateShelterApprovedStatus(id, redirectAttributes, false);
    }

    private ModelAndView updateShelterApprovedStatus(long id, RedirectAttributes redirectAttributes, boolean target) {
        if(userInfo.getUser() == null) {
            // Not logged in.
            redirectAttributes.addFlashAttribute("message", "You must be logged in to perform that action.");
            return new ModelAndView("redirect:/");
        } else if(!(userInfo.getUser() instanceof Administrator)) {
            // Not an administrator.
            redirectAttributes.addFlashAttribute("message", "You must be an administrator in order to perform that action.");
            return new ModelAndView("redirect:/");
        } else if(!shelterBo.setShelterApprovedStatus(id, target)) {
            // Not an administrator.
            redirectAttributes.addFlashAttribute("message", "Failed to change the approved flag for the provided shelter.");
            return new ModelAndView("redirect:/shelter/");
        } else {
            redirectAttributes.addFlashAttribute("message", "Shelter updated!");
            return new ModelAndView("redirect:/shelter/");
        }
    }
}
