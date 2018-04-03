package com.janusz.Controller;

import com.janusz.Entity.Contact;
import com.janusz.Entity.Person;
import com.janusz.Service.ContactService;
import com.janusz.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private PersonService personService;

    @Autowired
    private ContactService contactService;

    @GetMapping(value = "/newContact")
    public String newContact(@RequestParam Long personId, HttpServletRequest request){
        request.setAttribute("personId", personId);
        request.setAttribute("mode", "MODE_NEW_CONTACT");
        return "contacts";
    }

    @PostMapping(value = "/saveContact")
    public String saveContact(@ModelAttribute Contact contact, BindingResult result, @RequestParam Long personId, HttpServletRequest request){

        Person person = personService.getPersonById(Long.parseLong(request.getParameter("personId")));
        contact.setPerson(person);
        contactService.saveContact(contact);
        List<Contact> contacts = null;
        try{
            contacts = person.getContacts();
        }catch (NullPointerException ex){
            //logs...
        }        request.setAttribute("personId", personId);
        request.setAttribute("contacts", contacts);
        request.setAttribute("mode", "MODE_ALL_CONTACTS");
        return "contacts";
    }

    @GetMapping(value = "/updateContact")
    public String updateContact(@RequestParam Long contactId, HttpServletRequest request, @RequestParam Long personId){
        request.setAttribute("contact", contactService.getContactById(contactId));
        request.setAttribute("mode", "MODE_UPDATE_CONTACT");
        request.setAttribute("personId", personId);
        return "contacts";
    }

    @GetMapping(value = "/deleteContact")
    public String deleteContact(@RequestParam Long contactId, @RequestParam Long personId, HttpServletRequest request){
        Person person = personService.getPersonById(personId);
        List<Contact> contacts = null;
        try{
            contacts = person.getContacts();
        }catch (NullPointerException ex){
            //logs...
        }
        request.setAttribute("contacts", contacts);
        request.setAttribute("mode", "MODE_ALL_CONTACTS");
        request.setAttribute("personId", personId);

        contactService.deleteContact(contactId);
        return "contacts";
    }

    @GetMapping(value = "/allContacts")
    public String personsContacts(@RequestParam Long personId, HttpServletRequest request){

        Person person = personService.getPersonById(personId);
        List<Contact> contacts = null;
        try{
            contacts = person.getContacts();
        }catch (NullPointerException ex){
            //logs...
        }
        request.setAttribute("personId", personId);
        request.setAttribute("contacts", contacts);
        request.setAttribute("mode", "MODE_ALL_CONTACTS");
        return "contacts";
    }


}
