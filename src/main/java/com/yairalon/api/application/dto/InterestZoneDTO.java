package com.yairalon.api.application.dto;



public class InterestZoneDTO {

    private  String name;
    private double angle;
    private String zodiacsign;


    public InterestZoneDTO() {}


    public InterestZoneDTO(String name, double angle, String zodiacSign) {
        this.name = name;
        this.angle = angle;
        this.zodiacsign = zodiacSign;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public String getZodiacsign() {
        return zodiacsign;
    }

    public void setZodiacsign(String zodiacsign) {
        this.zodiacsign = zodiacsign;
    }



}
