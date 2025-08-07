package com.yairalon.api.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;


import java.time.Instant;
import java.time.LocalDateTime;


@MongoEntity(database = "cabastro", collection = "user-data")
public class UserData {

    private int UTC ;
    public ObjectId id;
    private String userUuid;

    private Instant createat;
    private Instant UpdateDate;
    private LocalDateTime lastLogin;

    private String name;
    private String email;
    private String phone;
    private String currentcity;

    private LocalDateTime birthDateTime;

    private double currentlocationlong;
    private double currentlocationlat;

    private double birthlocationlong;
    private double birthlocationlat;


    private String gender;
    private String address;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String authId;


    public UserData(String UUID, LocalDateTime UpdateDate, LocalDateTime lastLogin, String name, String email, String phone, String currentcity, LocalDateTime birthDateTime, double currentlocationlong, double currentlocationlat, double birthlocationlong, double birthlocationlat) {

        this.userUuid = java.util.UUID.randomUUID().toString();

        this.UpdateDate = Instant.now();
        this.lastLogin = lastLogin;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.currentcity = currentcity;
        this.birthDateTime = birthDateTime;
        this.currentlocationlong = currentlocationlong;
        this.currentlocationlat = currentlocationlat;
        this.birthlocationlong = birthlocationlong;
        this.birthlocationlat = birthlocationlat;

    }

    public UserData(String name,
                    String email,
                    String phone,
                    String currentcity,
                    LocalDateTime birthDateTime,
                    double currentlocationlong,
                    double currentlocationlat,
                    double birthlocationlong,
                    double birthlocationlat,
                    String gender,
                    String address,
                    String city,
                    String state,
                    String country,
                    String zipcode,
                    int UTC,
                    String authId) {
        this.userUuid = java.util.UUID.randomUUID().toString();
        this.createat = Instant.now();
        this.UpdateDate = Instant.now();
        this.lastLogin = lastLogin;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.currentcity = currentcity;
        this.birthDateTime = birthDateTime;
        this.currentlocationlong = currentlocationlong;
        this.currentlocationlat = currentlocationlat;
        this.birthlocationlong = birthlocationlong;
        this.birthlocationlat = birthlocationlat;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.UTC = UTC;
        this.authId = authId;
    }

    public UserData() {


    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String UUID) {
        this.userUuid = UUID;
    }

    public Instant getCreateat() {
        return createat;
    }

    public void setCreateat(Instant createat) {
        this.createat = createat;
    }

    public Instant getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        UpdateDate = updateDate;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCurrentcity() {
        return currentcity;
    }

    public void setCurrentcity(String currentcity) {
        this.currentcity = currentcity;
    }

    public LocalDateTime getBirthDateTime() {
        return birthDateTime;
    }

    public void setBirthDateTime(LocalDateTime birthDateTime) {
        this.birthDateTime = birthDateTime;
    }

    public double getCurrentlocationlong() {
        return currentlocationlong;
    }

    public void setCurrentlocationlong(double currentlocationlong) {
        this.currentlocationlong = currentlocationlong;
    }

    public double getCurrentlocationlat() {
        return currentlocationlat;
    }

    public void setCurrentlocationlat(double currentlocationlat) {
        this.currentlocationlat = currentlocationlat;
    }

    public double getBirthlocationlong() {
        return birthlocationlong;
    }

    public void setBirthlocationlong(double birthlocationlong) {
        this.birthlocationlong = birthlocationlong;
    }

    public double getBirthlocationlat() {
        return birthlocationlat;
    }

    public void setBirthlocationlat(double birthlocationlat) {
        this.birthlocationlat = birthlocationlat;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public int getUTC() {
        return this.UTC;
    }

    public void setUTC(int UTC) { this.UTC = UTC; }
}
