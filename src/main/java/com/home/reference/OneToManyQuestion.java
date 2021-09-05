package com.home.reference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "q200")
public class OneToManyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "qname")
    private String qname;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="qid")
    @OrderColumn(name="type")
    private List<OneToManyAnswer> answers;

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

    public List<OneToManyAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<OneToManyAnswer> answers) {
        this.answers = answers;
    }
}
