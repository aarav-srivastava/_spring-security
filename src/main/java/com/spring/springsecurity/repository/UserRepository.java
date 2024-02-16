package com.spring.springsecurity.repository;

import com.spring.springsecurity.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserProfile,Integer>{

    public UserProfile findByLoginName(String loginName);

}
