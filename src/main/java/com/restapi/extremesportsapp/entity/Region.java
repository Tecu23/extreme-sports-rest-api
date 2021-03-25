package com.restapi.extremesportsapp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="regions")
public class Region {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="regions_id")
    private List<Location> locations;


    //constructors

    public Region() {}

    public Region(String name) {
        this.name = name;
    }

    //getter and setter for id

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //getter and setter for name

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //getter and setter for locations

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    //method for adding locations

    public void addLocation(Location location) {
        if (locations == null) locations = new ArrayList<>();

        locations.add(location);
    }

    //to string method

    @Override
    public String toString() {
        return "Region [id=" + id + ", name=" + name + "]";
    }

}

