package com.example.springsercurityclass35.entities;

import com.example.springsercurityclass35.entities.enums.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private RoleType name;
    public Role(RoleType name) {
        this.name = name;
    }

    private Set<User> users = new HashSet<>();
}
