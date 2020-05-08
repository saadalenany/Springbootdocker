package com.saad.springbootdocker.models;

import javax.persistence.*;

@Entity
@Table(name = "country_language")
public class CountryLanguage {

    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    @Column(name = "language")
    private String language;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
