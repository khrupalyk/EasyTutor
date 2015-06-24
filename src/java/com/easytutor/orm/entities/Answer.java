package com.easytutor.orm.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 22.06.15.
 */

@Entity
@Table(name = "answers")
public class Answer implements Serializable {

    private int answerId;
    private String content;
    private List<Question> questions = new ArrayList<>();

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "answer_id")
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "answers")
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", content='" + content + '\'' +
                ", questions=" + questions +
                '}';
    }
}
