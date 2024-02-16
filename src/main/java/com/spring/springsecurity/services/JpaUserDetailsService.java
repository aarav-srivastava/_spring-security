package com.spring.springsecurity.services;

import com.spring.springsecurity.entity.UserProfile;
import com.spring.springsecurity.repository.UserRepository;
import com.spring.springsecurity.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        UserProfile userProfile = userRepository.findByLoginName(username);
        System.out.println(userProfile);
        if(userProfile == null) throw new UsernameNotFoundException("username "+username+" doesn't exist");
        return new SecurityUser(userProfile);
    }
}
