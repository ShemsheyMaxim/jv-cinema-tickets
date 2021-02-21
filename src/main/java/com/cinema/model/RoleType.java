package com.cinema.model;

public enum RoleType {
    USER("User"), ADMIN("Admin");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
