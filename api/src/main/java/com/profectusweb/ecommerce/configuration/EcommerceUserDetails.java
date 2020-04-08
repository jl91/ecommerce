package com.profectusweb.ecommerce.configuration;

import com.profectusweb.ecommerce.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.LinkedList;

public class EcommerceUserDetails implements UserDetails {

    private UserEntity user;

    public EcommerceUserDetails(UserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new LinkedList<>();
        grantedAuthorities.add(this.user.getRole());
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.user.getDeletedAt() == null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.user.getDeletedAt() == null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.user.getDeletedAt() == null;
    }

    @Override
    public boolean isEnabled() {
        return this.user.getDeletedAt() == null;
    }
}
