package com.yairalon.api.application.dto;

import java.util.UUID;

public class AspectsDTO {
    private String id;

    private String planetName1;

    private String planetName2;

    private String Aspect;

    private int house;

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }



    public AspectsDTO(String planet1, String planet2, String aspect, int house) {
        this.id = UUID.randomUUID().toString();
        this.planetName1 = planet1;
        this.planetName2 = planet2;
        this.Aspect = aspect;
        this.house = house;
    }

    public String getAspect() {
        return Aspect;
    }

    public void setAspect(String aspect) {
        Aspect = aspect;
    }

    public String getPlanetName2() {
        return planetName2;
    }

    public void setPlanetName2(String planetName2) {
        this.planetName2 = planetName2;
    }


    public String getPlanetName1() {
        return planetName1;
    }

    public void setPlanetName1(String planetName1) {
        this.planetName1 = planetName1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
