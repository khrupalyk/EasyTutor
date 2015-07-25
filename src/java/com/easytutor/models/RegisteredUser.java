package com.easytutor.models;

import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * Created by root on 24.07.15.
 */
public class RegisteredUser implements Serializable {

    private String username;
    private String password;
    private String email;
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Length(min = 6, max = 12)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmedPassword) {
        this.confirmPassword = confirmedPassword;
    }
}
