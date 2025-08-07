package com.yairalon.api.model;

import org.bson.types.ObjectId;

public class Cuspides {

    private ObjectId id;

    private double cuspLongitude;

    private int house;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public double getCuspLongitude() {
        return cuspLongitude;
    }

    public void setCuspLongitude(double cuspLongitude) {
        this.cuspLongitude = cuspLongitude;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public Cuspides(double cuspLongitude, int house) {
        this.cuspLongitude = cuspLongitude;
        this.house = house;
    }
}
