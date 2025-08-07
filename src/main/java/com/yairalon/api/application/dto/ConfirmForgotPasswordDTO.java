package com.yairalon.api.application.dto;

public class ConfirmForgotPasswordDTO {
    public String email;
    public String newPassword;
    public String confirmationCode;

    public ConfirmForgotPasswordDTO() {}

    public ConfirmForgotPasswordDTO(String email, String newPassword, String confirmationCode) {
        this.email = email;
        this.newPassword = newPassword;
        this.confirmationCode = confirmationCode;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}