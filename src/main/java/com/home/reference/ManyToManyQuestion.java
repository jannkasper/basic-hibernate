package com.home.reference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "q201")
public class ManyToManyQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "qname")
    private String qname;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name ="ques_ans201",
            joinColumns = {@JoinColumn(name = "q_id")},
            inverseJoinColumns = {@JoinColumn(name = "ans_id")})
    private Set<ManyToManyAnswer> answers;

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

    public Set<ManyToManyAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<ManyToManyAnswer> answers) {
        this.answers = answers;
    }
}
