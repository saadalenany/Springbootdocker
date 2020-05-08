package com.saad.springbootdocker.controllers;

import com.saad.springbootdocker.models.Country;
import com.saad.springbootdocker.repos.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @GetMapping("/{code}")
    public Country getCountryByCode(@PathVariable("code") String code) {
        try {
            return countryRepository.findById(code).orElseThrow(() -> new RuntimeException("INVALID_COUNTRY_CODE"));
        } catch (Exception ex) {
            throw new RuntimeException("INTERNAL_ERROR");
        }
    }
}
