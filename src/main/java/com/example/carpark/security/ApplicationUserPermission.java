package com.example.carpark.security;

public enum ApplicationUserPermission {
    READ_PERMISSION("permission:read"),
    ADD_PERMISSION("permission:add"),
    EDIT_PERMISSION("permission:edit"),
    DELETE_PERMISSION("permission:delete");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
