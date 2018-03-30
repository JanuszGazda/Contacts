package com.janusz.Service;


import com.janusz.Entity.Person;
import com.janusz.Repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PersonService {

    @Autowired
    PersonRepository personRepository;


    public List<Person> getAllPersons(){
        List<Person> persons = new ArrayList<>();
        personRepository.findAll().forEach(persons::add);
        return persons;
    }

    public Person getPersonById(Long id){
        return personRepository.findOne(id);
    }

    public void savePerson(Person person){
        personRepository.save(person);
    }

    public void deletePerson(Long id){
        personRepository.delete(id);
    }

}
