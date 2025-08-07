package com.yairalon.api.application.dto;

public class CuspDTO {
    private double cuspLongitude;

    private int house;

    public CuspDTO() {}

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public CuspDTO(double cuspLongitude) {
        this.cuspLongitude = cuspLongitude;
    }

    public CuspDTO(double cuspLongitude, int house) {
        this.cuspLongitude = cuspLongitude;
        this.house = house;
    }

    public double getCuspLongitude() {
        return cuspLongitude;
    }

    public void setCuspLongitude(double cuspLongitude) {
        this.cuspLongitude = cuspLongitude;
    }
}