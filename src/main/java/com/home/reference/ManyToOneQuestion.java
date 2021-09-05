package com.home.reference;

import javax.persistence.*;

@Entity
@Table(name = "q204")
public class ManyToOneQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "qname")
    private String qname;

    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false)
    private ManyToOneAnswer answer;

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

    public ManyToOneAnswer getAnswer() {
        return answer;
    }

    public void setAnswer(ManyToOneAnswer answer) {
        this.answer = answer;
    }
}
