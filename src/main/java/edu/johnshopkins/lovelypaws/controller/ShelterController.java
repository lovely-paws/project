package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.AdoptionRequestResult;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.AdoptionRequestBo;
import edu.johnshopkins.lovelypaws.entity.*;
import edu.johnshopkins.lovelypaws.bo.ShelterBo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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
    public ModelAndView viewAll(ModelMap modelMap) {
        modelMap.put("shelters", shelterBo.getAllShelters());
        return new ModelAndView("shelter/view-all");
    }

    @RequestMapping(path = {"/register"}, method = RequestMethod.GET)
    public ModelAndView register(HttpServletRequest httpServletRequest) {
        if(userInfo.getUser() == null) {
            return new ModelAndView("shelter/register");
        } else {
            // The user is already logged in. They can't re-register.
            return new ModelAndView("/index");
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

    // TODO: Remove GET support (and @PathVariable) - we don't want users mucking around with URLs.
    @RequestMapping(path = "/create", method = {RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView createShelter(@PathVariable Map<String, String> pathVariables, @RequestParam Map<String, String> requestParams) {
        Map<String, String> map = (requestParams.size() > 0) ? requestParams : pathVariables;
        Address address = new Address();
        address.setLine1(map.get("line1"));
        address.setLine2(map.get("line2"));
        address.setCity(map.get("city"));
        address.setState(map.get("state"));
        address.setZip(map.get("zip"));

        // TODO: Error handling and animal types.
        Shelter shelter = shelterBo.createShelter(map.get("username"),
                DigestUtils.sha512Hex(map.get("password")),
                map.get("name"), map.get("description"), address,
                map.get("phoneNumber"), new ArrayList<>());

        // Redirect to the mapping for /shelter/view/{id} using the new ID.
        return new ModelAndView("redirect:/shelter/view/"+shelter.getId());
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
