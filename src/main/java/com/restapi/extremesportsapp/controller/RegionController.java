package com.restapi.extremesportsapp.controller;


import com.restapi.extremesportsapp.entity.Location;
import com.restapi.extremesportsapp.entity.Region;
import com.restapi.extremesportsapp.service.RegionServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1")
public class RegionController {

    private final RegionServiceClass service;

    @Autowired
    public RegionController(RegionServiceClass service) {
        this.service = service;
    }

    //mapping for showing all regions with all their location and sports

    @GetMapping("/regions")
    public List<Region> getRegions(){return service.getAll();}

    //mapping fo showing a region by id

    @GetMapping("/regions/{regionId}")
    public Region getRegion(@PathVariable Integer regionId){

        return service.getOne(regionId);

    }

    //mapping for adding a region to the database without linking it to a country

    @PostMapping("/regions")
    public Region addRegion(@RequestBody Region region ){

        region.setId(0);

        service.saveItem(region);

        return region;
    }

    //mapping for adding a location to a region specified by id
    @PostMapping("/regions/addLocation/{regionId}")
    public Location addLocation(@PathVariable Integer regionId, @RequestBody Location location) {

        service.addLocation(regionId, location);

        return location;
    }
    //mapping for updating a sport in the database

    @PutMapping("/regions")
    public Region updateRegion(@RequestBody Region region){

        service.saveItem(region);

        return region;
    }

    //mapping for deleting a sport from a database
    @DeleteMapping("/regions/{regionId}")
    public String deleteRegion(@PathVariable Integer regionId){

        Region region = service.getOne(regionId);

        if(region==null) throw new RuntimeException("That region didn't exist in the database");

        service.deleteItemById(regionId);

        return "The region has been deleted";
    }



}
