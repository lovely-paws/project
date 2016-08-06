package edu.johnshopkins.lovelypaws.controller;

import edu.johnshopkins.lovelypaws.Role;
import edu.johnshopkins.lovelypaws.beans.AnimalTypeInfo;
import edu.johnshopkins.lovelypaws.beans.ServerResponse;
import edu.johnshopkins.lovelypaws.beans.UserInfo;
import edu.johnshopkins.lovelypaws.bo.AnimalTypeBo;
import edu.johnshopkins.lovelypaws.dao.AnimalTypeDao;
import edu.johnshopkins.lovelypaws.entity.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Controller
@RequestMapping(value = {"/animal-type", "/animal-type/"})
@Scope("session")
public class AnimalTypeController {

    @Autowired
    private UserInfo userInfo;

    @Autowired
    private AnimalTypeBo animalTypeBo;

    @Autowired
    private AnimalTypeDao animalTypeDao;

    @RequestMapping(path = "/create.do")
    public ModelAndView doCreate(AnimalTypeInfo animalTypeInfo, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null || userInfo.getUser().getRole() != Role.ADMINISTRATOR) {
            redirectAttributes.addFlashAttribute("message", "You are not authorized to perform that action.");
            return new ModelAndView("redirect:/");
        }

        ServerResponse<AnimalType> response = animalTypeBo.update(animalTypeInfo);

        if(!response.isSuccess()) {
            return new ModelAndView("/animal-type/create")
                    .addObject("message", response.getMessage())
                    .addObject("animalTypeInfo", animalTypeInfo);
        } else {
            redirectAttributes.addFlashAttribute("message", "Animal type created!");
            return new ModelAndView("redirect:/animal-type");
        }
    }

    @RequestMapping(path = "/create")
    public ModelAndView create(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null || userInfo.getUser().getRole() != Role.ADMINISTRATOR) {
            redirectAttributes.addFlashAttribute("message", "You are not authorized to perform that action.");
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("/animal-type/create")
                .addObject("animalTypeInfo", new AnimalTypeInfo());
    }

    @RequestMapping(path = "/edit/{id}")
    public ModelAndView edit(@PathVariable(value = "id") long id, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null || userInfo.getUser().getRole() != Role.ADMINISTRATOR) {
            redirectAttributes.addFlashAttribute("message", "You are not authorized to perform that action.");
            return new ModelAndView("redirect:/");
        }

        AnimalType animalType = animalTypeDao.findById(id);
        if(animalType == null) {
            redirectAttributes.addFlashAttribute("message", "No such animal type.");
            return new ModelAndView("redirect:/animal-type");
        } else {
            return new ModelAndView("/animal-type/edit")
                    .addObject("animalTypeInfo", new AnimalTypeInfo(animalType));
        }
    }

    @RequestMapping(path = "/edit.do")
    public ModelAndView doEdit(AnimalTypeInfo animalTypeInfo, RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null || userInfo.getUser().getRole() != Role.ADMINISTRATOR) {
            redirectAttributes.addFlashAttribute("message", "You are not authorized to perform that action.");
            return new ModelAndView("redirect:/");
        }

        ServerResponse<AnimalType> response = animalTypeBo.update(animalTypeInfo);
        if(!response.isSuccess()) {
            if(animalTypeInfo != null) {
                redirectAttributes.addFlashAttribute("message", response.getMessage());
                redirectAttributes.addFlashAttribute("animalTypeInfo", animalTypeInfo);
                return new ModelAndView("redirect:/animal-type/edit/"+animalTypeInfo.getId());
            } else {
                redirectAttributes.addFlashAttribute("message", "Invalid request.");
                return new ModelAndView("redirect:/");
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Animal type updated!");
            return new ModelAndView("redirect:/animal-type");
        }
    }

    @RequestMapping(path = {"", "/"})
    public ModelAndView viewAll(RedirectAttributes redirectAttributes) {
        if(userInfo.getUser() == null || userInfo.getUser().getRole() != Role.ADMINISTRATOR) {
            redirectAttributes.addFlashAttribute("message", "You are not authorized to perform that action.");
            return new ModelAndView("redirect:/");
        }

        return new ModelAndView("/animal-type/view-all")
                .addObject("animalTypes", new ArrayList<AnimalType>(animalTypeDao.findAll()));
    }
}
