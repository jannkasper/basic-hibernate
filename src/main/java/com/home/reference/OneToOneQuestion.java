package com.home.reference;

import javax.persistence.*;

@Entity
@Table(name = "q202")
public class OneToOneQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "qname")
    private String qname;

    @OneToOne(cascade = CascadeType.ALL)
    private OneToOneAnswer answer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public OneToOneAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(OneToOneAnswer answer) {
        this.answer = answer;
    }
}
