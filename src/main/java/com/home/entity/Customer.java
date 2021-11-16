package com.home.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@Data
@Accessors(chain = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "CustomerID")),
        @AttributeOverride(name = "name", column = @Column(name = "CustomerName"))
})
public class Customer extends AbstractNameEntity {

    @Column(name = "ContactName")
    private String contactName;
    @Embedded
    private Address address;
}
