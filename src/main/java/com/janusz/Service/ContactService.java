package com.janusz.Service;

import com.janusz.Entity.Contact;
import com.janusz.Entity.Person;
import com.janusz.Repository.ContactRepository;
import com.janusz.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PersonRepository personRepository;

    public List<Contact> getAllContacts(Long id, Person person){
        List<Contact> contacts = new ArrayList<>();
        contactRepository.findAll().forEach(contacts::add);
        return contacts;
    }

    public Contact getContactById(Long id){
        return contactRepository.findOne(id);
    }

    public void saveContact(Contact contact){
        contactRepository.save(contact);
    }

    public void deleteContact(Long id){
        contactRepository.delete(id);
    }

}
