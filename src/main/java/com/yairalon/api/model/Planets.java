package com.yairalon.api.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(database = "cabastro", collection = "planets")
public class Planets {
    private ObjectId id;
    private String UUID;
    private String planetName;
    private double planetLongitude;
    private String zodiacSign;
    private int house;
    private double cupangle;

    public double getCupangle() {
        return cupangle;
    }

    public void setPlanetLongitude(double planetLongitude) {
        this.planetLongitude = planetLongitude;
    }

    public boolean isRetrograde() {
        return retrograde;
    }

    public void setRetrograde(boolean retrograde) {
        this.retrograde = retrograde;
    }

    private boolean retrograde;

    public Planets(String planetName, double planetLongitude, String zodiacSign, int house, boolean retrograde, double cuspangle) {
        this.planetName = planetName;
        this.UUID = java.util.UUID.randomUUID().toString();
        this.planetName = planetName;
        this.planetLongitude = planetLongitude;
        this.zodiacSign = zodiacSign;
        this.house = house;
        this.cupangle = cuspangle;
    }

    public String getZodiacSign() {
        return zodiacSign;
    }

    public void setZodiacSign(String zodiacSign) {
        this.zodiacSign = zodiacSign;
    }

    public String getUUID() {
        return UUID;
    }

    public void setCupangle(double cupangle) {
        this.cupangle = cupangle;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }



}
