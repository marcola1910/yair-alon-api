package com.yairalon.api.model;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(database = "cabastro", collection = "sign")
public class Sign {

    private String id;
    private String UUID;
    private String name;
    private String description_en;
    private String description_pt;
    private String description_es;
    private String description_he;
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription_en() {
        return description_en;
    }

    public void setDescription_en(String description_en) {
        this.description_en = description_en;
    }

    public String getDescription_pt() {
        return description_pt;
    }

    public void setDescription_pt(String description_pt) {
        this.description_pt = description_pt;
    }

    public String getDescription_es() {
        return description_es;
    }

    public void setDescription_es(String description_es) {
        this.description_es = description_es;
    }

    public String getDescription_he() {
        return description_he;
    }

    public void setDescription_he(String description_he) {
        this.description_he = description_he;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Sign( String name, String description_en, String description_pt, String description_es, String description_he, String image) {
        this.UUID = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.description_en = description_en;
        this.description_pt = description_pt;
        this.description_es = description_es;
        this.description_he = description_he;
        this.image = image;
    }

}
