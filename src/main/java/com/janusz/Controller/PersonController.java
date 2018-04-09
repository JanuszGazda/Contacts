package com.janusz.Controller;


import com.janusz.Entity.Person;
import com.janusz.Service.ContactService;
import com.janusz.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/all")
    public String getAllPersons(HttpServletRequest request){
        request.setAttribute("people", personService.getAllPersons());
        request.setAttribute("mode", "MODE_ALL");
        return "index";
    }

    @GetMapping(value = "/newPerson")
    public String newPerson(HttpServletRequest request){
        request.setAttribute("mode", "MODE_NEW");
        return "index";
    }

    @PostMapping(value = "/savePerson")
    public String savePerson(@ModelAttribute Person person, BindingResult result,
                             HttpServletRequest request, ModelMap model){

        if(personService.getPersonById(person.getPesel())==null) {
            personService.savePerson(person);
            request.setAttribute("people", personService.getAllPersons());
            request.setAttribute("mode", "MODE_ALL");
        }else {
            request.setAttribute("errorDuplicate", "Nie mozna dodac dwoch kontaktow o identycznym nr Pesel");
            request.setAttribute("mode", "MODE_NEW");
            request.setAttribute("people", personService.getAllPersons());
        }
        return "index";
    }

    @PostMapping(value = "/saveUpdatedPerson")
    public String saveUpdatedPerson(@ModelAttribute Person person, BindingResult result,
                             HttpServletRequest request, ModelMap model){

        personService.savePerson(person);
        request.setAttribute("people", personService.getAllPersons());
        request.setAttribute("mode", "MODE_ALL");
        return "index";
    }

    @GetMapping(value = "/updatePerson")
    public String updatePerson(@RequestParam Long personId, HttpServletRequest request){
        request.setAttribute("person", personService.getPersonById(personId));
        request.setAttribute("mode", "MODE_UPDATE");
        return "index";
    }

    @GetMapping(value = "/deletePerson")
    public String deletePerson(@RequestParam Long personId, HttpServletRequest request){
        personService.deletePerson(personId);
        request.setAttribute("people", personService.getAllPersons());
        request.setAttribute("mode", "MODE_ALL");
        return "index";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

}
