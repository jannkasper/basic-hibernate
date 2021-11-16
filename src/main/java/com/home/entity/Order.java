package com.home.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@Data
@Accessors(chain = true)
@AttributeOverride(name = "id", column = @Column(name = "OrderID"))
public class Order extends AbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    private Customer customer;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID")
    private Employee employee;
    @Column(name = "OrderDate")
    private Date date;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ShipperID", referencedColumnName = "ShipperID")
    private Shipper shipper;
}
