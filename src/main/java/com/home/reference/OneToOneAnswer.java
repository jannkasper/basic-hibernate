package com.home.reference;

import javax.persistence.*;

@Entity
@Table(name = "ans202")
public class OneToOneAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "answerName")
    private String answerName;

    @Column(name = "postedBy")
    private String postedBy;

    @OneToOne(mappedBy = "answer")
    private OneToOneQuestion question;


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

    public OneToOneQuestion getQuestion() {
        return question;
    }

    public void setQuestion(OneToOneQuestion question) {
        this.question = question;
    }
}
