package com.janusz.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.*;

@Entity

public class Person {

    @Id
    @Column(name="pesel")
    private Long pesel;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="sex")
    private boolean sex;

    @OneToMany( fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    private List<Contact> contacts = new ArrayList<>();

    @Column(name="date")
    //Temporal(TemporalType.DATE)
    private String date;

    public Person(String name, String surname, Long pesel, boolean sex, String date){
        this.name=name;
        this.surname=surname;
        this.pesel=pesel;
        this.sex=sex;
        this.date=date;
        //this.contact=contact;
    }

    public Person(){}

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addContact( Contact contact){
        this.contacts.add(contact);
    }
}
