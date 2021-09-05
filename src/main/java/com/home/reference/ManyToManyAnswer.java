package com.home.reference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ans201")
public class ManyToManyAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "answerName")
    private String answerName;

    @Column(name = "postedBy")
    private String postedBy;

    @ManyToMany(mappedBy = "answers")
    private Set<ManyToManyQuestion> questions;


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

    public Set<ManyToManyQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<ManyToManyQuestion> questions) {
        this.questions = questions;
    }
}
