package com.yairalon.api.application.dto;

import java.util.List;

public class UserMapDTO {

    private List<InterestZoneDTO> interestZones;
    private String userUuid;
    private UserDTO user;
    private List<PlanetsDTO> planet;
    private List<AspectsDTO> aspects;
    private List<CuspDTO> cusp;
    private List<FixedStarDTO> fix;

    public UserMapDTO() {}

    public UserMapDTO(String userUuid, UserDTO user, List<PlanetsDTO> planet, List<AspectsDTO> aspects, List<CuspDTO> cusp, List<FixedStarDTO> fix, List<InterestZoneDTO> interestZone) {
        this.userUuid = userUuid;
        this.user = user;
        this.planet = planet;
        this.aspects = aspects;
        this.cusp = cusp;
        this.fix = fix;
        this.interestZones = interestZone;

    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<PlanetsDTO> getPlanet() {
        return planet;
    }

    public void setPlanet(List<PlanetsDTO> planet) {
        this.planet = planet;
    }

    public List<AspectsDTO> getAspects() {
        return aspects;
    }

    public void setAspects(List<AspectsDTO> aspects) {
        this.aspects = aspects;
    }

    public List<CuspDTO> getCusp() {
        return cusp;
    }

    public void setCusp(List<CuspDTO> cusp) {
        this.cusp = cusp;
    }

    public List<FixedStarDTO> getFix() {
        return fix;
    }

    public void setFix(List<FixedStarDTO> fix) {
        this.fix = fix;
    }

    public List<InterestZoneDTO> getInterestZones() {
        return interestZones;
    }

    public void setInterestZones(List<InterestZoneDTO> interestZones) {
        this.interestZones = interestZones;
    }
}
