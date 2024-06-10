package com.telran.shopDemo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "OrderItems")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
    @Id
    @Column(name = "OrderItemId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderItemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderId",nullable = false)
    private Order order;

    @Column(name = "ProductId")
    private long productId;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "PriceAtPurchase")
    private BigDecimal priceAtPurchase;
}
