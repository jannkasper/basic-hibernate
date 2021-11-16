package com.home.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@MappedSuperclass
@Data
@Accessors(chain = true)
public class AbstractNameEntity extends AbstractEntity {

    @Column(name = "name")
    private String name;
}
