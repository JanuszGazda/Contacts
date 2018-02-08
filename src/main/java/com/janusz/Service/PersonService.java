package com.janusz.Service;


import com.janusz.Dao.PersonDao;
import com.janusz.Entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public Collection<Person> getAllPersons(){
        return personDao.getAllPersons();
    }

    public Person getPersonById(int id){
        return this.personDao.getPersonById(id);
    }
}
