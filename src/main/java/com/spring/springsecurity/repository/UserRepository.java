package com.spring.springsecurity.repository;

import com.spring.springsecurity.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserProfile,Integer>{

    public UserProfile findByLoginName(String loginName);

}
