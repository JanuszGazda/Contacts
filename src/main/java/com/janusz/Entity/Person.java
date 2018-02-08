package com.janusz.Entity;


import java.util.Date;

public class Person {

    private String name, surname, pesel;
    private boolean sex;
    private Contact contact;
    private Date date;

    public Person(String name, String surname, String pesel, boolean sex, Date date, Contact contact){
        this.name=name;
        this.surname=surname;
        this.pesel=pesel;
        this.sex=sex;
        this.date=date;
        this.contact=contact;
    }

    public Person(){}

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }




}
