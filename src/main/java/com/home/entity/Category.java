package com.home.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "categories")
@Data
@Accessors(chain = true)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "CategoryID")),
        @AttributeOverride(name = "name", column = @Column(name = "CategoryName"))
})
public class Category extends AbstractNameEntity {

    @Column(name = "Description")
    private String description;
}
