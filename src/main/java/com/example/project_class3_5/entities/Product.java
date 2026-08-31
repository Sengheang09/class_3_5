package com.example.project_class3_5.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="tbl_product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    private BigDecimal price;

    @NotNull
    private int stock;
    // product *-----1 category
    @ManyToOne
    @JoinColumn(name="category_id" , nullable = false)
    private Category category;

    // 1 product have many orderItem

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();

}
