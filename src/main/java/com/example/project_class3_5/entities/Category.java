package com.example.project_class3_5.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "name must not be black")
    @Size(min=2 , max=100 , message = "name must be between 2 to 100 characters")// (size>=2 && size<=100)
    private String name;

    @Size(max = 1000 , message = "description must be less than 1000")
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // category 1-----* product
    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
}
