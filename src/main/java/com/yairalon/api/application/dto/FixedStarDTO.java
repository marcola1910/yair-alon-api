package com.yairalon.api.application.dto;

import java.util.UUID;

public class FixedStarDTO {
    private String id;
    private String starName;
    private int houseNumber;
    private double position;

    public FixedStarDTO() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public FixedStarDTO(String starName, int houseNumber, double position) {
        this.id =  UUID.randomUUID().toString();;
        this.starName = starName;
        this.houseNumber = houseNumber;
        this.position = position;
    }
}
