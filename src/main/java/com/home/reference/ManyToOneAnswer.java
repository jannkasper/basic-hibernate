package com.home.reference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ans204")
public class ManyToOneAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "answerName")
    private String answerName;

    @Column(name = "postedBy")
    private String postedBy;

    @OneToMany(mappedBy = "answer")
    private Set<ManyToOneQuestion> questions;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public Set<ManyToOneQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<ManyToOneQuestion> questions) {
        this.questions = questions;
    }
}
