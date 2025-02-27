package com.yairalon.api.application.dto;

import java.time.LocalDateTime;

public class TransitCalculationRequestDTO {
    private LocalDateTime dateTime;
    private BirthChartResponseDTO birthChart;

    public TransitCalculationRequestDTO() {}

    public TransitCalculationRequestDTO(LocalDateTime dateTime, BirthChartResponseDTO birthChart) {
        this.dateTime = dateTime;
        this.birthChart = birthChart;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BirthChartResponseDTO getBirthChart() {
        return birthChart;
    }

    public void setBirthChart(BirthChartResponseDTO birthChart) {
        this.birthChart = birthChart;
    }

    @Override
    public String toString() {
        return "TransitCalculationRequestDTO{" +
                "dateTime=" + dateTime +
                ", natalChart=" + birthChart +
                '}';
    }
}
