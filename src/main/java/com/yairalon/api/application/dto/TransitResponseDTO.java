package com.yairalon.api.application.dto;

import java.util.ArrayList;
import java.util.List;

public class TransitResponseDTO {
    private BirthChartResponseDTO birthChart;
    private List<AspectsDTO> transitAspects;

    public TransitResponseDTO(BirthChartResponseDTO natalChart, List<AspectsDTO> transitAspects) {
        this.birthChart = natalChart;
        this.transitAspects = transitAspects;
    }

    public TransitResponseDTO() {
        this.transitAspects = new ArrayList<AspectsDTO>();
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