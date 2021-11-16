package com.home.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "shippers")
@Data
@Accessors(chain = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "ShipperID")),
        @AttributeOverride(name = "name", column = @Column(name = "ShipperName"))
})
public class Shipper extends AbstractNameEntity {

    @Column(name = "Phone")
    private String phone;

}
