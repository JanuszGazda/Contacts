package com.janusz.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")
//@EntityListeners(AuditingEntityListener.class)

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

    //private Contact contact;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    private Date date;

    public Person(String name, String surname, Long pesel, boolean sex, Date date){
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
