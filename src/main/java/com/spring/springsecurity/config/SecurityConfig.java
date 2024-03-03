package com.spring.springsecurity.config;

import com.spring.springsecurity.filters.ApiKeyFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Value("${security-key}")
    private String key;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic(Customizer.withDefaults())
                .addFilterBefore(new ApiKeyFilter(key), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(customizer->{
                    customizer.anyRequest().authenticated();
                })
                .build();
    }
}
