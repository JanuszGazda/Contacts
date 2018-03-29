package com.janusz.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "person",
                fetch = FetchType.LAZY,
                cascade = CascadeType.ALL)
    private Set<Contact> contact = new HashSet<>();

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

    public Long getPesel() {
        return pesel;
    }

    public void setPesel(Long pesel) {
        this.pesel = pesel;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
