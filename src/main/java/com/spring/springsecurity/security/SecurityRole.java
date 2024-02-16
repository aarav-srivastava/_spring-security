package com.spring.springsecurity.security;

import com.spring.springsecurity.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
@AllArgsConstructor
public class SecurityRole implements GrantedAuthority {
    private Role role;
    @Override
    public String getAuthority() {
        return role.getRoleName();
    }
}
