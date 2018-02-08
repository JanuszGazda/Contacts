package com.janusz.Dao;


import com.janusz.Entity.Contact;
import com.janusz.Entity.Person;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PersonDao {

    private static Map<Integer, Person> people;

    static {
        people = new HashMap<Integer, Person>(){
            {
                put(1, new Person("Jan", "Kowalski", "32338766553", true,
                        new Date(), new Contact()));
                put(2, new Person("Andrzej", "Tak", "22348886553", true,
                        new Date(), new Contact()));
                put(3, new Person("Ktoś", "Nowak", "96933456553", true,
                        new Date(), new Contact()));
            }
        };

    }

    public Collection<Person> getAllPersons(){
        return this.people.values();
    }

    public Person getPersonById(int id){
        return this.people.get(id);
    }
}
