package com.example.confirmingauth.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.confirmingauth.security.AppUserPermissions.*;

public enum AppUserRoles {

    NORMAL_USER(Sets.newHashSet(PRODUCT_READ)),
    ADMIN_USER(Sets.newHashSet(CATEGORY_READ, CATEGORY_WRITE, PRODUCT_READ, PRODUCT_WRITE));

    private final Set<AppUserPermissions> permissions;

    AppUserRoles(Set<AppUserPermissions> permissions) {
        this.permissions = permissions;
    }

    public Set<AppUserPermissions> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissions()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE" + this.name()));
        return permissions;
    }
}
