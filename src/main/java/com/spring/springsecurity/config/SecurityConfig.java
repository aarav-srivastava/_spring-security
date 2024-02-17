package com.spring.springsecurity.config;

import com.spring.springsecurity.config.security.filters.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomAuthenticationFilter customAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity
                    .addFilterAt(customAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // custom authentication filter // add the new filter at the place of UsernamePasswordAuthenticationFilter which httpBasic relies on.
                    .authorizeHttpRequests(customizer->{
                        customizer.anyRequest().authenticated(); // all the requests.
                    })
                    .build();
    }
}
