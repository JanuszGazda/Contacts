package com.janusz.Controller;

import com.janusz.Entity.Contact;
import com.janusz.Repository.ContactRepository;
import com.janusz.Repository.PersonRepository;
import com.janusz.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/{userId}/contacts")
public class ContactRestController {

    private final ContactRepository contactRepository;
    private final PersonRepository personRepository;

    @Autowired
    ContactRestController(ContactRepository contactRepository,
                          PersonRepository personRepository){
            this.contactRepository = contactRepository;
            this.personRepository = personRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Contact> readContacts(@PathVariable Long userId){
        this.validateUser(userId);
        return this.contactRepository.findByPersonPesel(userId);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable Long userId, @RequestBody Contact input){
        this.validateUser(userId);

        return this.personRepository
                   .findByPesel(userId)
                   .map(person -> {
                            Contact result = contactRepository.save(new Contact(person,
                                    input.getPhoneNumber(), input.getEmail(), input.getAddress()));

                            URI location = ServletUriComponentsBuilder
                                            .fromCurrentRequest().path("/{id}")
                                            .buildAndExpand(result.getId()).toUri();

                            return ResponseEntity.created(location).build();
                    })
                    .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{contactId}")
    Contact readContact(@PathVariable Long userId, @PathVariable Long contactId){
        this.validateUser(userId);
        return this.contactRepository.findOne(contactId);
    }


    private void validateUser(Long userId) {
        this.personRepository.findByPesel(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
    }
}
