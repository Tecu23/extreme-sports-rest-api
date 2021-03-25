package com.restapi.extremesportsapp.service;

import com.restapi.extremesportsapp.entity.Location;
import com.restapi.extremesportsapp.entity.Sport;
import com.restapi.extremesportsapp.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class LocationServiceClass implements Service<Location>{


    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceClass(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAll() {

        return locationRepository.findAll();
    }

    @Override
    public Location getOne(Integer id) {
        Optional<Location> result = locationRepository.findById(id);

        Location location = null;

        if(result.isPresent()){
            location = result.get();
        }else{
            throw new RuntimeException("Did not find the location id - "+id);
        }

        return location;
    }

    @Override
    public void saveItem(Location location) {

        locationRepository.save(location);

    }


    @Override
    public void deleteItemById(Integer id) {

        locationRepository.deleteById(id);
    }

    public void addSport(Integer locationId, Sport sport){

        Optional<Location> result = locationRepository.findById(locationId);

        Location location = null;


        if(result.isPresent()){
            location = result.get();
            location.addSport(sport);
            locationRepository.save(location);
        }else{
            throw new RuntimeException("The id you requested for the location id invalid " + locationId);
        }

    }

    public List<Location> getAllSportsByNames(String [] names){

        List<Location> allLocations = locationRepository.findAll();
        List<Location> okLocations = new ArrayList<Location>();

        for(Location location : allLocations){
            boolean ok = false;
            for(Sport sport : location.getSports()){
                for(String name:names){
                    if(name.equals(sport.getName()) && !ok){
                        okLocations.add(location);
                        ok = true;
                        break;
                    }
                    if(ok){
                        break;
                    }
                }
            }
        }

        return okLocations;
    }
}
