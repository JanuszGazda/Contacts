package com.janusz.Controller;

import com.janusz.Entity.Contact;
import com.janusz.Entity.Person;
import com.janusz.Service.ContactService;
import com.janusz.Service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    PersonService personService;

    @Autowired
    ContactService contactService;

    @GetMapping(value = "/newContact")
    public String newContact(@RequestParam Long personId, HttpServletRequest request){
        request.setAttribute("personId", personId);
        request.setAttribute("mode", "MODE_NEW_CONTACT");
        return "contacts";
    }

    @PostMapping(value = "/saveContact")
    public String saveContact(@ModelAttribute Contact contact, BindingResult result, @RequestParam Long id, HttpServletRequest request){

        Person person = personService.getPersonById(Long.parseLong(request.getParameter("personId")));
        contact.setPerson(person);
        System.out.println(request.getParameter("personId"));
        contactService.saveContact(contact);
        List<Contact> contacts = person.getContacts();
        request.setAttribute("contacts", contacts);
        request.setAttribute("mode", "MODE_ALL_CONTACTS");
        return "contacts";
    }

    @GetMapping(value = "/updateContact")
    public String updateContact(@RequestParam Long contactId, HttpServletRequest request){
        request.setAttribute("contact", contactService.getContactById(contactId));
        request.setAttribute("mode", "MODE_UPDATE_CONTACT");
        return "contacts";
    }

    @GetMapping(value = "/deleteContact")
    public String deleteContact(@RequestParam Long contactId, @RequestParam Long personId, HttpServletRequest request){
        Person person = personService.getPersonById(personId);
        List<Contact> contacts = person.getContacts();
        request.setAttribute("contacts", contacts);
        request.setAttribute("mode", "MODE_ALL_CONTACTS");

        contactService.deleteContact(contactId);
        return "contacts";
    }

    @GetMapping(value = "/allContacts")
    public String personsContacts(@RequestParam Long id, HttpServletRequest request){

        Person person = personService.getPersonById(id);
        List<Contact> contacts = person.getContacts();
        request.setAttribute("personId", id);
        request.setAttribute("contacts", contacts);
        request.setAttribute("mode", "MODE_ALL_CONTACTS");
        return "contacts";
    }

}
