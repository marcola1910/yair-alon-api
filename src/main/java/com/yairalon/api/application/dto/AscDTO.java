package com.yairalon.api.application.dto;

import java.util.UUID;

public class AscDTO {
    private String id;
    private String ZodiacName;

    private int houseNumber;
    private double position;

    public AscDTO(double ascendant, String zodiacSign) {
        this.ZodiacName = zodiacSign;
        this.id = UUID.randomUUID().toString();
        this.houseNumber = 1;
        this.position = ascendant;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZodiacName() {
        return ZodiacName;
    }

    public void setZodiacName(String zodiacName) {
        ZodiacName = zodiacName;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

}
