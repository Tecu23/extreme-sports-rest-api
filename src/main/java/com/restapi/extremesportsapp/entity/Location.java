package com.restapi.extremesportsapp.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.restapi.extremesportsapp.serializers.CustomSportSerializer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="locations")
public class Location {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade= {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name="sports_has_locations",
            joinColumns=@JoinColumn(name="locations_id"),
            inverseJoinColumns=@JoinColumn(name="sports_id"))
    @JsonSerialize(using= CustomSportSerializer.class)
    private List<Sport> sports;

    //constructors

    public Location() {}

    public Location(String name) {
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

    //getter and setter for sports

    public List<Sport> getSports() {
        return sports;
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
    }

    //method for adding a sport to a location

    public void addSport(Sport sport) {
        if(sports==null) sports = new ArrayList<>();

        sports.add(sport);
    }

    //to string method

    @Override
    public String toString() {
        return "Location [id=" + id + ", name=" + name + "]";
    }

}

