package com.restapi.extremesportsapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name="countries")
public class Country {

    @Column(name="name")
    private String name;

    @Id
    @Column(name="iso_code")
    private char[] isoCode = new char[2];

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="countries_iso_code")
    private List<Region> regions;

    //constructor

    public Country() {}

    public Country(String name , char[] isoCode) {
        this.name = name;
        this.isoCode = isoCode;

    }



    // getter and setter for Name

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //getter and setter for ISO code

    public char[] getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(char[] isoCode) {
        this.isoCode = isoCode;
    }

    //getter and setter for regions

    public List<Region> getRegions() {
        return regions;
    }

    public void setRegions(List<Region> regions) {
        this.regions = regions;
    }
    //adding regions method

    public void addRegion(Region region) {

        if(regions == null) regions = new ArrayList<>();

        regions.add(region);
    }

    //to string method

    @Override
    public String toString() {
        return "Country [name=" + name + ", isoCode=" + Arrays.toString(isoCode) + "]";
    }

}
