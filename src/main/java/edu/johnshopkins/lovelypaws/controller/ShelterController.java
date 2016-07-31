package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.AdoptionRequestResult;
import edu.johnshopkins.lovelypaws.beans.ShelterData;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.AdoptionRequestBo;
import edu.johnshopkins.lovelypaws.entity.*;
import edu.johnshopkins.lovelypaws.bo.ShelterBo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView viewAll() {
        return new ModelAndView("shelter/view-all")
                .addObject("shelters", shelterBo.getAllShelters());
    }

    @RequestMapping(path = {"/register"}, method = RequestMethod.GET)
    public ModelAndView register() {
        if(userInfo.getUser() == null) {
            return new ModelAndView("shelter/register")
                    .addObject("shelterData", new ShelterData());
        } else {
            return new ModelAndView("redirect:/")
                    .addObject("message", "You must log out in order to register.");
        }
    }

    @RequestMapping(path = "/requests")
    public ModelAndView requests() {
        Shelter shelter = shelterBo.getShelter(userInfo.getUser().getId());
        Collection<AdoptionRequest> requests = shelterBo.getAdoptionRequests((Shelter)shelter);
        requests.removeIf(new Predicate<AdoptionRequest>() {
            @Override
            public boolean test(AdoptionRequest adoptionRequest) {
                return adoptionRequest.getAdoptionRequestResult() != AdoptionRequestResult.PENDING;
            }
        });
        return new ModelAndView("shelter/requests")
                .addObject("requests", requests);
    }

    @RequestMapping(path = "/create")
    public ModelAndView createShelter(@ModelAttribute("shelterData") ShelterData shelterData) {
        try {
            Shelter shelter = shelterBo.createShelter(shelterData.getUsername(),
                    DigestUtils.sha512Hex(shelterData.getPasswordSha512()),
                    shelterData.getName(), shelterData.getDescription(), shelterData.getAddressData(),
                    shelterData.getPhoneNumber(), shelterData.getEmailAddress(), new ArrayList<>());
            userInfo.setUser(shelter);
            return new ModelAndView("redirect:/account");
        } catch(Exception exception) {
            return new ModelAndView("/shelter/register")
                    .addObject("message", exception.getMessage());
        }
    }

    @RequestMapping(path = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView list(ModelMap modelMap, @PathVariable long id) {
        modelMap.put("shelter", shelterBo.getShelter(id));
        return new ModelAndView("shelter/view");
    }

    @RequestMapping(path = "/close/{id}")
    public ModelAndView close(@PathVariable Long id, @RequestParam(name = "result") Boolean result) {
        adoptionRequestBo.close(id, result);
        return new ModelAndView("/index")
                .addObject("message", "Closed!");
    }
}
