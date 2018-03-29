package com.janusz.Controller;


import com.janusz.Entity.Person;
import com.janusz.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
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
    public String savePerson(@ModelAttribute Person person, BindingResult result, HttpServletRequest request, HttpServletResponse response){
        System.out.println("person.date="+request.getAttribute("person.date"));

        try {
            personService.savePerson(person);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        request.setAttribute("people", personService.getAllPersons());
        request.setAttribute("mode", "MODE_ALL");
        return "index";
    }

    @GetMapping(value = "/updatePerson")
    public String updatePerson(@RequestParam Long id, HttpServletRequest request){
        request.setAttribute("person", personService.getPersonById(id));
        request.setAttribute("mode", "MODE_UPDATE");
        return "index";
    }

    @GetMapping(value = "/deletePerson")
    public String deletePerson(@RequestParam Long id, HttpServletRequest request){
        personService.deletePerson(id);
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
