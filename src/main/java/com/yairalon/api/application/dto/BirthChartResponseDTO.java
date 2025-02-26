package com.yairalon.api.application.dto;

import java.util.ArrayList;
import java.util.List;

public class BirthChartResponseDTO {
    private BirthChartRequestDTO inputData;
    private AscDTO Ascendent;
    private List<PlanetsDTO> planets;
    private List<AspectsDTO> aspects;


    public BirthChartResponseDTO(){
        this.planets = new ArrayList<>();
        this.aspects = new ArrayList<>();
    }
    public BirthChartRequestDTO getInputData() {
        return inputData;
    }

    public void setInputData(BirthChartRequestDTO inputData) {
        this.inputData = inputData;
    }

    public AscDTO getAscendent() {
        return Ascendent;
    }

    public void setAscendent(AscDTO ascendent) {
        Ascendent = ascendent;
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


}
