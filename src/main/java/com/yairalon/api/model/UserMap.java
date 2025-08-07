package com.yairalon.api.model;

import com.yairalon.api.application.dto.*;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@MongoEntity(database = "cabastro", collection = "user-map")
public class UserMap {

    public ObjectId id;
    private String useruuid;

    private List<PlanetsDTO> planets;
    private List<AspectsDTO> aspects;
    private List<CuspDTO> cuspides;
    private List<FixedStarDTO> fixedStars;
    private List<InterestZoneDTO> interestZones;

    public UserMap() {}

    public UserMap(String useruuid, List<PlanetsDTO> planets, List<AspectsDTO> aspects, List<CuspDTO> cuspides, List<FixedStarDTO> fixedStars, List<InterestZoneDTO> interestZones) {
        this.useruuid = useruuid;
        this.planets = planets;
        this.aspects = aspects;
        this.cuspides = cuspides;
        this.fixedStars = fixedStars;
        this.interestZones = interestZones;

    }

    public String getUseruuid() {
        return useruuid;
    }

    public void setUseruuid(String useruuid) {
        this.useruuid = useruuid;
    }

    public List<PlanetsDTO> getPlanets() {
        return planets;
    }

    public void setPlanets(List<PlanetsDTO> planets) {
        this.planets = planets;
    }

    public List<AspectsDTO> getAspects() {
        return aspects;
    }

    public void setAspects(List<AspectsDTO> aspects) {
        this.aspects = aspects;
    }

    public List<CuspDTO> getCuspides() {
        return cuspides;
    }

    public void setCuspides(List<CuspDTO> cuspides) {
        this.cuspides = cuspides;
    }

    public List<FixedStarDTO> getFixedStars() {
        return fixedStars;
    }

    public void setFixedStars(List<FixedStarDTO> fixedStars) {
        this.fixedStars = fixedStars;
    }

    public List<InterestZoneDTO> getInterestZones() {
        return interestZones;
    }

    public void setInterestZones(List<InterestZoneDTO> interestZones) {
        this.interestZones = interestZones;
    }
}
