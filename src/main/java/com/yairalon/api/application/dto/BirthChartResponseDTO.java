package com.yairalon.api.application.dto;

import java.util.ArrayList;
import java.util.List;

public class BirthChartResponseDTO {
    private BirthChartRequestDTO inputData;
    private List<PlanetsDTO> planets;
    private List<AspectsDTO> aspects;
    private List<CuspDTO> cuspides;
    private List<FixedStarDTO> fixedStarDTOS;

    public List<InterestZoneDTO> getInterestZone() {
        return interestZone;
    }

    public void setInterestZone(List<InterestZoneDTO> interestZone) {
        this.interestZone = interestZone;
    }

    private List<InterestZoneDTO> interestZone;

    public List<CuspDTO> getCuspides() {
        return cuspides;
    }

    public void setCuspides(List<CuspDTO> cuspides) {
        this.cuspides = cuspides;
    }

    public BirthChartResponseDTO(){
        this.planets = new ArrayList<>();
        this.aspects = new ArrayList<>();
        this.cuspides = new ArrayList<>();
        this.interestZone = new ArrayList<>();
        this.fixedStarDTOS = new ArrayList<>();
    }
    public BirthChartRequestDTO getInputData() {
        return inputData;
    }

    public void setInputData(BirthChartRequestDTO inputData) {
        this.inputData = inputData;
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

    public List<FixedStarDTO> getFixedStars() {
        return fixedStarDTOS;
    }

    public void setFixedStars(List<FixedStarDTO> fixedStarDTOS) {
        this.fixedStarDTOS = fixedStarDTOS;
    }
}
