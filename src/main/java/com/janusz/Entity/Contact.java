package com.janusz.Entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contact {

    @ManyToOne
    private Person person;

    @Id
    @GeneratedValue
    private Long id;

    private int phoneNumber;
    private String email, address;

    private Contact(){}

    public Contact(final Person person, final int phoneNumber, final String email, final String address){
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public Long getId(){
        return id;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
