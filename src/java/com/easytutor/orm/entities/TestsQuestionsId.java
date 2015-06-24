package com.easytutor.orm.entities;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by root on 24.06.15.
 */

@Embeddable
public class TestsQuestionsId implements Serializable {

    private Question question;
    private Test test;
    private UserATutor userATutor;
    private Answer selectedAnswer;

    @ManyToOne
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne
    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    @ManyToOne
    public UserATutor getUserATutor() {
        return userATutor;
    }

    public void setUserATutor(UserATutor userATutor) {
        this.userATutor = userATutor;
    }

    @ManyToOne
    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Answer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }
}

