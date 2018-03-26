package com.janusz.Controller;


import com.janusz.Entity.Person;
import com.janusz.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
//@RequestMapping("/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/people")
    public String getAllPersons(ModelMap model){

        return personService.getAllPersons().toString();
    }


    @GetMapping("/people/{id}")
    public Person getPerson(@PathVariable Long id){
        return personService.getPersonById(id);
    }

    @GetMapping(value = "/addPerson")
    public void addPerson(@RequestParam Long pesel, @RequestParam String name,
                          @RequestParam String surname, @RequestParam boolean sex){
        Person person = new Person(name, surname, pesel, sex, new Date());
        personService.addPerson(person);
    }

    @GetMapping(value = "/deletePerson")
    public void deletePerson(@RequestParam Long id){
        personService.deletePerson(id);
    }

}
