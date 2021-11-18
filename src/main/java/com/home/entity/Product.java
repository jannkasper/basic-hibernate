package com.home.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@Accessors(chain = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ProductID")),
        @AttributeOverride(name = "name", column = @Column(name = "ProductName"))
})
public class Product extends AbstractNameEntity {

//    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SupplierID", referencedColumnName = "SupplierID")
    private Supplier supplier;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    private Category category;
    @Column(name = "Unit")
    private String unit;
    @Column(name = "Price")
    private double price;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", name=" + getName() +
                ", unit='" + unit + '\'' +
                ", price=" + price +
                '}';
    }
}
