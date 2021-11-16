package com.home.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "suppliers")
@Data
@Accessors(chain = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "SupplierID")),
        @AttributeOverride(name = "name", column = @Column(name = "SupplierName"))
})
public class Supplier extends AbstractNameEntity {

    @Column(name = "ContactName")
    private String contactName;
    @Embedded
    Address address;
//    @JsonManagedReference
//    @OneToMany(mappedBy = "supplier", targetEntity = Product.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Product> products = new HashSet<Product>();

}
