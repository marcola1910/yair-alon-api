package com.yairalon.api.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(database = "cabastro", collection = "fixedstars")
public class FixedStars {

    private ObjectId id;
    private String UUID;
    private String starName;
    private int houseNumber;
    private double position;


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

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public FixedStars(String starName, int houseNumber, double position) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.starName = starName;
        this.houseNumber = houseNumber;
        this.position = position;
    }
}
