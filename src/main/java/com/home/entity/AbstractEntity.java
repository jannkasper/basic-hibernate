package com.home.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@MappedSuperclass
@Data
@Accessors(chain = true)
public class AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
}
