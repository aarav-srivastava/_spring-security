package com.spring.springsecurity.security;

import com.spring.springsecurity.entity.UserProfile;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SecurityUser implements UserDetails {
    private UserProfile userProfile;
    @Override
    public String getUsername() {
        return userProfile.getLoginName();
    }

    @Override
    public String getPassword() {
        return userProfile.getPwdHash();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<? extends GrantedAuthority> list = userProfile.getRoles().stream().map(SecurityRole::new).collect(Collectors.toList());
        System.out.println("roles are : "+list);
        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
