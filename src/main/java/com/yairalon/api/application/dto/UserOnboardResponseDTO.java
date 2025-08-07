package com.yairalon.api.application.dto;

import java.time.Instant;

public class UserOnboardResponseDTO {
    private String UUID;
    private String ID;
    private String email;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserOnboardResponseDTO(String UUID, String email) {
        this.UUID = UUID;
        this.email = email;
    }
}
