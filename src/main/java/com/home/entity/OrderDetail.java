package com.home.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Data
@Accessors(chain = true)
@AttributeOverride(name = "id", column = @Column(name = "OrderDetailID"))
public class OrderDetail extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    private Order order;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    private Product product;
    @Column(name = "Quantity")
    private int quantity;
}
