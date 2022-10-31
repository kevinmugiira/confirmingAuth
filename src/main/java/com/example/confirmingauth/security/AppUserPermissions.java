package com.example.confirmingauth.security;

public enum AppUserPermissions {

    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write");

    private final String permissions;

    AppUserPermissions(String permissions) {
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}
