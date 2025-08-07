package com.yairalon.api.application.dto;

import java.util.ArrayList;
import java.util.List;

public class TransitResponseDTO {
    private BirthChartResponseDTO birthChart;
    private List<AspectsDTO> transitAspects;

    public List<CuspDTO> getCuspides() {
        return cuspides;
    }

    public void setCuspides(List<CuspDTO> cuspides) {
        this.cuspides = cuspides;
    }

    public void setBirthChart(BirthChartResponseDTO birthChart) {
        this.birthChart = birthChart;
    }

    private List<CuspDTO> cuspides;



    public TransitResponseDTO(BirthChartResponseDTO natalChart, List<AspectsDTO> transitAspects) {
        this.birthChart = natalChart;
        this.transitAspects = transitAspects;
        this.cuspides = new ArrayList<>();
    }

    public TransitResponseDTO() {
        this.transitAspects = new ArrayList<AspectsDTO>();
        this.cuspides = new ArrayList<>();
    }

    public BirthChartResponseDTO getBirthChart() {
        return birthChart;
    }

    public void setNatalChart(BirthChartResponseDTO birthChart) {
        this.birthChart = birthChart;
    }

    public List<AspectsDTO> getTransitAspects() {
        return transitAspects;
    }

    public void setTransitAspects(List<AspectsDTO> transitAspects) {
        this.transitAspects = transitAspects;
    }
}