package com.yairalon.api.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public class BirthChartRequestDTO {

    private String ID;
    private LocalDateTime birthday;
    private String name;
    private double latPlace;
    private double longPlace;
    private int utc;
    private String country;
    private String city;
    private String state;
    private String zip;

    public BirthChartRequestDTO() {
        this.ID = UUID.randomUUID().toString();
    }

    public double getLongPlace() {
        return longPlace;
    }

    public void setLongPlace(double longPlace) {
        this.longPlace = longPlace;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatPlace() {
        return latPlace;
    }

    public void setLatPlace(double latPlace) {
        this.latPlace = latPlace;
    }

    public int getUtc() {
        return utc;
    }

    public void setUtc(int utc) {
        this.utc = utc;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
