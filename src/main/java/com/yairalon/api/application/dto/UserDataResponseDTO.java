package com.yairalon.api.application.dto;

public class UserDataResponseDTO {


    private UserDTO userData;
    private UserMapDTO userMap;

    public UserDataResponseDTO(){

    }
    public UserDataResponseDTO(UserDTO userData, UserMapDTO userMap) {
        this.userData = userData;
        this.userMap = userMap;
    }

    public void setUserData(UserDTO userData) {
        this.userData = userData;
    }

    public UserMapDTO getUserMap() {
        return userMap;
    }

    public void setUserMap(UserMapDTO userMap) {
        this.userMap = userMap;
    }
}
