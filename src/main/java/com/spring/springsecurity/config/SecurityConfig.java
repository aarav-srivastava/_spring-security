package com.spring.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
            return httpSecurity.httpBasic(Customizer.withDefaults())
                    .authorizeHttpRequests(customizer->{
                        customizer

                                // authorization rules

//                                .anyRequest().authenticated(); //endpoint level authorization.
//                                .anyRequest().permitAll()
                                .anyRequest().hasAuthority("read");
//                                .anyRequest().hasAnyAuthority("read","write");
//                                  .anyRequest().hasRole("ADMIN");
//                                .anyRequest().access("isAuthenticated() and hasAuthority('read')"); //deprecated.

                        // matcher methods


                    }) // role is badge whereas authority is action allowed.
                    .build();

            //customizer.(matcher method + authorization rule)
    }
    @Bean
    public UserDetailsService userDetailsService(){ // using .roles("ADMIN) is same as using .authorities("ROLE_ADMIN") // using ROLE_ as prefix in authorities
        UserDetails userDetails1 = User.withUsername("aarav").password(passwordEncoder.encode("admin")).authorities("write").roles("SUPER_ADMIN").build(); //role = SUPER_ADMIN
        UserDetails userDetails2 = User.withUsername("aman").password(passwordEncoder.encode("admin")).authorities("read","ROLE_ADMIN").build(); // role = ADMIN
        return new InMemoryUserDetailsManager(userDetails1,userDetails2);
    }
}
