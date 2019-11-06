package com.devaj.happens.model;

import com.devaj.happens.model.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Item  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JsonIgnore
    private Stock stock;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitation_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Solicitation solicitation;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private Status status;

    public Item(Stock stock, Integer amount, double price) {
        this.stock = stock;
        this.amount = amount;
        this.price = price;
    }
}
