package com.restapi.extremesportsapp.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.restapi.extremesportsapp.serializers.CustomLocationSerializer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="sports")
public class Sport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="start_date")
    private String startDate;

    @Column(name="end_date")
    private String endDate;

    @Column(name="medium_cost")
    private Double mediumCost;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade= {CascadeType.PERSIST,CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(
            name="sports_has_locations",
            joinColumns=@JoinColumn(name="sports_id"),
            inverseJoinColumns=@JoinColumn(name="locations_id"))
    @JsonSerialize(using= CustomLocationSerializer.class)
    private List<Location> locations;

    //constructors

    public Sport() {}

    public Sport(String name, String startDate, String endDate, Double mediumCost) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mediumCost = mediumCost;
    }

    public Sport(String name, String startDate, String endDate, Double mediumCost, List<Location> locations) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.mediumCost = mediumCost;
        this.locations = locations;
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

    //getter and setter for start_date

    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    //getter and setter for end_date

    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    //getter and setter for medium cost

    public Double getMediumCost() {
        return mediumCost;
    }
    public void setMediumCost(Double mediumCost) {
        this.mediumCost = mediumCost;
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

    //to String method

    @Override
    public String toString() {
        return "Sport [id=" + id + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
                + ", mediumCost=" + mediumCost + "]";
    }
}
