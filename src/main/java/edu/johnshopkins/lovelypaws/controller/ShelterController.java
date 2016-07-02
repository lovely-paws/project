package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.entity.Address;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import edu.johnshopkins.lovelypaws.bo.ShelterBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/shelter")
public class ShelterController {

    @Autowired
    private ShelterBo shelterBo;

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView viewAll() {
        return new ModelAndView("shelter/view-all");
    }

    // TODO: Remove GET support - we don't want users mucking around with URLs.
    @RequestMapping(path = "/create", method = {RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView createShelter(@PathVariable Map<String, String> pathVariables) {
        Address address = new Address();
        address.setLine1(pathVariables.get("line1"));
        address.setLine2(pathVariables.get("line2"));
        address.setCity(pathVariables.get("city"));
        address.setState(pathVariables.get("state"));
        address.setZip(pathVariables.get("zip"));

        // TODO: Error handling!
        Shelter shelter = shelterBo.createShelter(pathVariables.get("name"), pathVariables.get("passwordSha512"),
                pathVariables.get("name"), pathVariables.get("description"), address,
                pathVariables.get("phoneNumber"), new ArrayList<>());

        // Redirect to the mapping for /shelter/view/{id} using the new ID.
        return new ModelAndView("redirect:/shelter/view/"+shelter.getId());
    }

    @RequestMapping(path = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView list(ModelMap modelMap, @PathVariable long id) {
        modelMap.put("shelter", shelterBo.getShelter(id));
        return new ModelAndView("shelter/view");
    }
}
