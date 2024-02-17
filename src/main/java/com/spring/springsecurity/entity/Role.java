package com.spring.springsecurity.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String roleName;
    private String description;
    @ManyToMany(mappedBy = "roles")
    private Set<UserProfile> users;
}
