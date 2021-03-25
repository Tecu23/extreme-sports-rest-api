package com.restapi.extremesportsapp.controller;


import com.restapi.extremesportsapp.entity.Country;
import com.restapi.extremesportsapp.entity.Region;
import com.restapi.extremesportsapp.service.CountryServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1")
public class CountryController {

    private final CountryServiceClass service;

    @Autowired
    public CountryController(CountryServiceClass service) {
        this.service = service;
    }

    //mapping for showing all countries with their regions , locations and sports

    @GetMapping("/countries")
    public List<Country> getCountries() {return service.getAll();}

    //mapping for showing one country

    @GetMapping("/countries/{isoCode}")
    public Country getCountry(@PathVariable char[] isoCode){

        return service.getOne(isoCode);

    }
    //momentan nu merge sa adaug locatii cu sporturile
    //mapping for adding a country
    @PostMapping("/countries")
    public Country addCountry(@RequestBody Country country){

        service.saveItem(country);

        return country;
    }
    //mapping for adding a region to a country specified by iso code
    @PostMapping("countries/addRegion/{isoCode}")
    public Region addRegion(@PathVariable char[] isoCode, @RequestBody Region region){

        service.addRegion(isoCode, region);

        return region;

    }
    //mapping for updating a country
    @PutMapping("/countries")
    public Country updateCountry(@RequestBody Country country){

        service.saveItem(country);

        return country;
    }

    //mapping for deleting a country by iso code
    @DeleteMapping("/countries/{isoCode}")
    public String deleteCountry(@PathVariable char[] isoCode){

        Country country = service.getOne(isoCode);

        if(country == null) throw new RuntimeException("That country does not exist in the database");

        service.deleteItemById(isoCode);

        return "The country has been deleted";
    }

}
