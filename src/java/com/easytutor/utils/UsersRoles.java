package com.easytutor.utils;

/**
 * Created by root on 25.07.15.
 */
public enum UsersRoles {
    USER("ROLE_USER"),ADMIN("ROLE_ADMIN");
    private String role;

    private UsersRoles(String s) {
        role = s;
    }

    public String getRole(){return role;}
}
