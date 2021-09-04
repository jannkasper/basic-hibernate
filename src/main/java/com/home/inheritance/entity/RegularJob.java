package com.home.inheritance.entity;

import javax.persistence.*;

@Entity
@Table(name = "regularjob")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id")),
        @AttributeOverride(name = "name", column = @Column(name = "name"))

})
public class RegularJob extends Job {
    @Column(name="salary")
    private float salary;
    @Column(name="bonus")
    private int bonus;

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
