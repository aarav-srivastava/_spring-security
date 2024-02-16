package com.spring.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public UserDetailsService setUpUser(){
        String password = passwordEncoder.encode("admin");
        UserDetails user1 = User.withUsername("aarav").password(password).build();
        UserDetails user2 = User.withUsername("aman").password(password).build();
        return new InMemoryUserDetailsManager(user1,user2);
    }
}
