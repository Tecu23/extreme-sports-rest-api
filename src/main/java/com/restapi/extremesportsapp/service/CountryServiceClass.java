package com.restapi.extremesportsapp.service;


import com.restapi.extremesportsapp.entity.Country;
import com.restapi.extremesportsapp.entity.Location;
import com.restapi.extremesportsapp.entity.Region;
import com.restapi.extremesportsapp.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class CountryServiceClass{

    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceClass(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }


    //retrieve all countries

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    //retrieve one country by id

    public Country getOne(char[] isoCode) {

        Optional<Country> result = countryRepository.findById(isoCode);

        Country country = null;

        if(result.isPresent()){
            country = result.get();
        }else{
            throw new RuntimeException("Did not find the country with the iso code- " + isoCode.toString());
        }

        return country;
    }

    //saving a country to the database

    public void saveItem(Country country) {

        countryRepository.save(country);
    }

    //deleting a country from the database

    public void deleteItemById(char[] isoCode) {
        countryRepository.deleteById(isoCode);
    }

    //adding a region to a country

    public void addRegion(char[] isoCode, Region region){

        Optional<Country> result = countryRepository.findById(isoCode);

        Country country = null;

        if(result.isPresent()){
            country = result.get();
            country.addRegion(region);
            countryRepository.save(country);
        }else{
            throw new RuntimeException("The id you requested for the region id invalid " + isoCode);
        }
    }

}
