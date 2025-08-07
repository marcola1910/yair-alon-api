package com.yairalon.api.application.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public class UserOnboardRequestDTO {

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
    private int birthUTC;

    private String pwd;

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

    public int getBirthUTC() {
        return this.birthUTC;
    }

    public void setBirthUTC(int birthUTC) {
        this.birthUTC = birthUTC;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
