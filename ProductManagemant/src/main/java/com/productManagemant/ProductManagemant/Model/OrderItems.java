package com.productManagemant.ProductManagemant.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItems {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
@ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
@ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
private Integer productCount;
}
