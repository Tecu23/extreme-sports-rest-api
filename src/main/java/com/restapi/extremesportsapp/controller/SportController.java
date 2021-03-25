package com.restapi.extremesportsapp.controller;

import com.restapi.extremesportsapp.entity.Location;
import com.restapi.extremesportsapp.entity.Sport;
import com.restapi.extremesportsapp.service.SportServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="api/v1")
public class SportController {

    private final SportServiceClass service;

    @Autowired
    public SportController(SportServiceClass service) {
        this.service = service;
    }


    //mapping for showing all sports with all of their locations

    @GetMapping("/sports")
    public List<Sport> getSports(){
        return service.getAll();
    }


    //mapping for showing a sport by id

    @GetMapping("/sports/{sportId}")
    public Sport getSport(@PathVariable Integer sportId){

        return service.getOne(sportId);
    }

    //mapping for adding a sport to the database

    @PostMapping("/sports")
    public Sport addSport(@RequestBody Sport sport){

        sport.setId(0);

        service.saveItem(sport);

        return sport;
    }

    //adding a location to a sport

    @PostMapping("/sports/addLocation/{sportId}")
    public Location addLocation(@PathVariable Integer sportId,@RequestBody Location location){

        service.addLocation(sportId, location);

        return location;
    }

    //mapping for updating a sport in the database

    @PutMapping("/sports")
    public Sport updateSport(@RequestBody Sport sport){

        service.saveItem(sport);

        return sport;
    }

    //mapping for deleting a sport from the database

    @DeleteMapping("/sports/{sportId}")
    public String deleteSports(@PathVariable Integer sportId ){

        Sport sport = service.getOne(sportId);

        if(sport == null) throw new RuntimeException("That sport didn't exist in the database");

        service.deleteItemById(sportId);

        return "The sport has been deleted.";
    }
    //mapping for retrieving all the sports that can be performed during that period
    @GetMapping("/sports/dates")
    public List<Sport> getAllSportsDate(@RequestParam String startDate , @RequestParam String endDate){
        System.out.println("Data de inceput " + startDate);
        System.out.println("Data de sfarsit " + endDate);
        System.out.println("data incepand de la indexul 3 este - " + startDate.substring(3));
        System.out.println("data incepand de la indexul 0 pana la 3 este - " + startDate.substring(0,2));
        return service.findDates(startDate,endDate);
    }

    //mapping for retrieving all the sport requested by names
    @GetMapping("/sports/names")
    public List<Sport> getAllSportsWithNames(@RequestParam String[] names){

        for(String s: names){
            System.out.println(s);
        }

        return new ArrayList<Sport>();
    }
}
