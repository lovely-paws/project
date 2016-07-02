package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.entity.Address;
import edu.johnshopkins.lovelypaws.entity.Shelter;
import edu.johnshopkins.lovelypaws.bo.ShelterBo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/shelter")
public class ShelterController {

    @Autowired
    private ShelterBo shelterBo;

    @RequestMapping(path = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView viewAll(ModelMap modelMap) {
        modelMap.put("shelters", shelterBo.getAllShelters());
        return new ModelAndView("shelter/view-all");
    }

    @RequestMapping(path = {"/register"}, method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("shelter/register");
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
        Shelter shelter = shelterBo.createShelter(map.get("name"),
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
}
