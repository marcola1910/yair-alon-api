package com.yairalon.api.application.dto;

public class UserBirthChartRegistrationResponse {
    String useruuid;

    public UserBirthChartRegistrationResponse(String useruuid) {
        this.useruuid = useruuid;
    }


    public String getUseruuid() { return useruuid; }
    public void setUseruuid(String useruuid) { this.useruuid = useruuid; }
}
