package com.janusz.Entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Person person;

    //1==E-mail 2==Telefon  3==Adres
    @NotNull
    private int type;

    @Column(name = "value")
    private String value;

    private Contact(){}

    public Contact(final Person person, final int type, final String value){
        this.type = type;
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPerson(Person person){
        this.person=person;
    }

    public Person getPerson(){
        return person;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPesel(){
        return this.person.getPesel();
    }
}
