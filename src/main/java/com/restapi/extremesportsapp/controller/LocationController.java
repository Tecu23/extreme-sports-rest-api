package com.restapi.extremesportsapp.controller;

import com.restapi.extremesportsapp.entity.Location;
import com.restapi.extremesportsapp.entity.Sport;
import com.restapi.extremesportsapp.service.LocationServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1")
public class LocationController {

    private final LocationServiceClass service;

    @Autowired
    public LocationController(LocationServiceClass service) {
        this.service = service;
    }


    //mapping for showing all locations with their sport

    @GetMapping("/locations")
    public List<Location> getLocations(){
        return service.getAll();
    }

    //mapping for showing a location by id

    @GetMapping("/locations/{locationId}")
    public Location getLocation(@PathVariable Integer locationId){

        return service.getOne(locationId);
    }

    //mapping for adding a location without the region associated

    @PostMapping("/locations")
    public Location addLocation(@RequestBody Location location){

        location.setId(0);

        service.saveItem(location);

        return location;
    }
    //mapping fot adding a sport to a location
    @PostMapping("/locations/addSport/{locationId}")
    public Sport addSport(@PathVariable Integer locationId,@RequestBody Sport sport){

        service.addSport(locationId,sport);

        return sport;
    }

    //mapping for updating a location in the database

    @PutMapping("/locations")
    public Location updateLocation(@RequestBody Location location){

        service.saveItem(location);

        return location;
    }

    //mapping for deleting a location from the database

    @DeleteMapping("/locations/{locationId}")
    public String deleteLocation(@PathVariable Integer locationId){

        Location location = service.getOne(locationId);

        if(location == null ) throw new RuntimeException("That location did not exist in the database");

        service.deleteItemById(locationId);

        return "The location has been deleted.";

    }

    @GetMapping("/locations/sports")
    public List<Location> getAllSportsNames(@RequestParam String[] names){

        return service.getAllSportsByNames(names);

    }

}
