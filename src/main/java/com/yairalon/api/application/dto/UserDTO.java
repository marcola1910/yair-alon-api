package com.yairalon.api.application.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public class UserDTO {

    private int UTC;

    private String UUID;
    private Instant createat;

    private LocalDateTime UpdateDate;
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

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Instant getCreateat() {
        return createat;
    }

    public void setCreateat(Instant createat) {
        this.createat = createat;
    }

    public LocalDateTime getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
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
        return this.currentlocationlong;
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
        return this.birthlocationlong;
    }

    public void setBirthlocationlong(double birthlocationlong) {
        this.birthlocationlong = birthlocationlong;
    }

    public double getBirthlocationlat() {
        return this.birthlocationlat;
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

    public UserDTO(
            String UUID,
            Instant createat,
            LocalDateTime updateDate,
            LocalDateTime lastLogin,
            String name,
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
            int UTC) {
        this.UUID = UUID;
        this.createat = createat;
        this.UpdateDate = updateDate;
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
    }

    public UserDTO() {
    }

    public int getUTC() {
        return UTC;
    }

    public void setUTC(int UTC) {
        this.UTC = UTC;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String setAuthId) {
        this.authId = setAuthId;
    }
}
