package com.example.springsercurityclass35.repo;

import com.example.springsercurityclass35.entities.Role;
import com.example.springsercurityclass35.entities.User;
import com.example.springsercurityclass35.entities.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(RoleType name);

}
