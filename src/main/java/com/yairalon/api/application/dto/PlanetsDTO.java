package com.yairalon.api.application.dto;

import java.util.UUID;

public class PlanetsDTO {

    private String id;
    private String planetName;
    private double planetLongitude;
    private String zodiacSign;
    private int house;

    public PlanetsDTO(String planetName, double planetLongitude, String zodiacSign, int house) {
        this.planetName = planetName;
        this.id = UUID.randomUUID().toString();
        this.planetName = planetName;
        this.planetLongitude = planetLongitude;
        this.zodiacSign = zodiacSign;
        this.house = house;
    }

    public String getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public double getPlanetLongitude() {
        return planetLongitude;
    }

    public void setPlanetLongitude(long planetLongitude) {
        this.planetLongitude = planetLongitude;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }



}
