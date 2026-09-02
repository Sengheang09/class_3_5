package com.example.project_class3_5.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="tbl_product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    private BigDecimal price;

    private String imageUrl;

    private String publicId;

    @NotNull
    private int stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id" , nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product")
    @Builder.Default
    private List<OrderItem> orderItems = new ArrayList<>();
}
