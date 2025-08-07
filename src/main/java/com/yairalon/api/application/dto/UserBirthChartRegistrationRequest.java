package com.yairalon.api.application.dto;

import java.util.List;

public class UserBirthChartRegistrationRequest {

    String userUUID;
    String useremail;

    List<PlanetsDTO> planets;
    List<CuspDTO> cuspDTOS;
    List<AspectsDTO> aspectDTOS;
    List<FixedStarDTO> fixedStarDTOS;

    public UserBirthChartRegistrationRequest(String userUUID, String useremail, List<PlanetsDTO> planets, List<CuspDTO> cuspDTOS, List<AspectsDTO> aspectDTOS, List<FixedStarDTO> fixedStarDTOS) {
        this.userUUID = userUUID;
        this.useremail = useremail;
        this.planets = planets;
        this.cuspDTOS = cuspDTOS;
        this.aspectDTOS = aspectDTOS;
        this.fixedStarDTOS = fixedStarDTOS;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public List<PlanetsDTO> getPlanets() {
        return planets;
    }

    public void setPlanets(List<PlanetsDTO> planets) {
        this.planets = planets;
    }

    public List<CuspDTO> getCuspDTOS() {
        return cuspDTOS;
    }

    public void setCuspDTOS(List<CuspDTO> cuspDTOS) {
        this.cuspDTOS = cuspDTOS;
    }

    public List<AspectsDTO> getAspectDTOS() {
        return aspectDTOS;
    }

    public void setAspectDTOS(List<AspectsDTO> aspectDTOS) {
        this.aspectDTOS = aspectDTOS;
    }

    public List<FixedStarDTO> getFixedStarDTOS() {
        return fixedStarDTOS;
    }

    public void setFixedStarDTOS(List<FixedStarDTO> fixedStarDTOS) {
        this.fixedStarDTOS = fixedStarDTOS;
    }
}
