package com.yairalon.api.model;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(database = "cabastro", collection = "transits_interpretations")
public class TransitsInterpretations {
    private String UUID;
    private String birthAstro;
    private String transitAstro;
    private String aspect;
    private int house;
    private String text;
    private String text_en;
    private String text_es;
    private String text_he;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getBirthAstro() {
        return birthAstro;
    }

    public void setBirthAstro(String birthAstro) {
        this.birthAstro = birthAstro;
    }

    public String getTransitAstro() {
        return transitAstro;
    }

    public void setTransitAstro(String transitAstro) {
        this.transitAstro = transitAstro;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText_en() {
        return text_en;
    }

    public void setText_en(String text_en) {
        this.text_en = text_en;
    }

    public String getText_es() {
        return text_es;
    }

    public void setText_es(String text_es) {
        this.text_es = text_es;
    }

    public String getText_he() {
        return text_he;
    }

    public void setText_he(String text_he) {
        this.text_he = text_he;
    }

    public String getAspect() {
        return aspect;
    }

    public void setAspect(String aspect) {
        this.aspect = aspect;
    }
}
