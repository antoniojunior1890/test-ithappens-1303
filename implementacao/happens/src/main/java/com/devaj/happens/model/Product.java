package com.devaj.happens.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 75, nullable = false)
    private String description;

    @Column(length = 20, unique = true, nullable = false)
    private String barCode;

    @Column(nullable = false)
    private double price;

    @OneToMany(mappedBy = "product")
    List<Stock> stocks;

    public Product(String name, String description, String barCode, Double price) {
        this.name = name;
        this.description = description;
        this.barCode = barCode;
        this.price = price;
    }

}
