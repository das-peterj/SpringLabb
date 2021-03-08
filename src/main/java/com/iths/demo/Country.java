package com.iths.demo;

import javax.persistence.*;

@Entity
@Table(name="countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String country;

    public Country(long id, String country) {
        this.id = id;
        this.country = country;
    }

    public Country(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
