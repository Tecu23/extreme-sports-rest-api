package com.restapi.extremesportsapp.service;

import com.restapi.extremesportsapp.entity.Location;
import com.restapi.extremesportsapp.entity.Region;
import com.restapi.extremesportsapp.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class RegionServiceClass implements Service<Region>{

    public final RegionRepository regionRepository;

    @Autowired
    public RegionServiceClass(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    //retrieving all regions

    @Override
    public List<Region> getAll() {
        return regionRepository.findAll();
    }

    //retrieving a region

    @Override
    public Region getOne(Integer id) {

        Optional<Region> result = regionRepository.findById(id);

        Region region = null;

        if(result.isPresent()){
            region = result.get();
        }else {
            throw new RuntimeException("Did not find the region id - " + id);
        }
        return region;
    }

    //saving a region to the database

    @Override
    public void saveItem(Region region) {

        regionRepository.save(region);
    }

    //deleting a region from the database

    @Override
    public void deleteItemById(Integer id) {

        regionRepository.deleteById(id);
    }

    //adding a location to a region

    public void addLocation(Integer regionId, Location location){

        Optional<Region> result = regionRepository.findById(regionId);

        Region region = null;

        if(result.isPresent()){
            region = result.get();
            region.addLocation(location);
            regionRepository.save(region);
        }else{
            throw new RuntimeException("The id you requested for the region id invalid " + regionId);
        }
    }
}
