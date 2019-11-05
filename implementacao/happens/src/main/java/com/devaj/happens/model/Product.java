package com.devaj.happens.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitation_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Solicitation solicitation;

    public Product(String name, String description, String barCode, Double price) {
        this.name = name;
        this.description = description;
        this.barCode = barCode;
        this.price = price;
    }


}
