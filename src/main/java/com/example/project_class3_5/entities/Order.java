package com.example.project_class3_5.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order" , cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
}
// git init
// git remote add origin https://github.com/Sengheang09/class_3_5.git
// git remote -v
// git checkout -b project
// git add .
// git commit -m "done with entities"
// git push origin project

