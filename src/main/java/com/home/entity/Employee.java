package com.home.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employees")
@Data
@Accessors(chain = true)
@AttributeOverride(name = "id", column = @Column(name = "EmployeeID"))
public class Employee extends AbstractEntity {

    @Column(name = "LastName")
    private String lastName;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "BirthDate")
    private Date birthDate;
    @Column(name = "Photo")
    private String photo;
    @Column(name = "Notes")
    private String notes;
    
}
