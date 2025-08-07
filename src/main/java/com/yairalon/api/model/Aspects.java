package com.yairalon.api.model;

import org.bson.types.ObjectId;

public class Aspects {

    private ObjectId id;

    private double longitude;

    private String planetName1;

    private String planetName2;

    private String Aspect;

    private int house;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPlanetName1() {
        return planetName1;
    }

    public void setPlanetName1(String planetName1) {
        this.planetName1 = planetName1;
    }

    public String getPlanetName2() {
        return planetName2;
    }

    public void setPlanetName2(String planetName2) {
        this.planetName2 = planetName2;
    }

    public String getAspect() {
        return Aspect;
    }

    public void setAspect(String aspect) {
        Aspect = aspect;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public Aspects(ObjectId id, double longitude, String planetName1, String planetName2, String aspect, int house) {
        this.id = id;
        this.longitude = longitude;
        this.planetName1 = planetName1;
        this.planetName2 = planetName2;
        Aspect = aspect;
        this.house = house;
    }
}
