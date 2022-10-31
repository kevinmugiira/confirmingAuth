package com.example.confirmingauth.models;


import com.example.confirmingauth.security.AppUserRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class AppUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long Id;

    @NotNull(message = "First name field is compulsory")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name field is compulsory")
    @Column(name = "last_name")
    private String lastName;

    @NotNull(message = "Email field is compulsory")
    @Column(name = "email")
    private String email;

    @NotNull(message = "The Mobile number field is compulsory")
    @Column(name = "phone")
    private String phone;

    @NotNull(message = "Password field is compulsory")
    @Length(min = 8, message = "password should be at least 8 characters long")
    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private AppUserRoles appUserRoles;

    private boolean locked = false;

    private boolean enabled = false;


    public AppUser(Long id,
                   String firstName,
                   String lastName,
                   String email,
                   String phone,
                   String password,
                   AppUserRoles appUserRoles) {
        Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.appUserRoles = appUserRoles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRoles.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || Hibernate.getClass(this) != Hibernate.getClass(obj)) return false;
        AppUser appUser = (AppUser) obj;
        return Id != null && Objects.equals(Id, appUser.Id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
