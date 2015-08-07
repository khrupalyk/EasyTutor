package com.easytutor.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by root on 24.06.15.
 */

@Embeddable
public class TestsQuestionsId implements Serializable {

    private Question question;
    private Test test;
    private Answer selectedAnswer;
    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.EAGER)
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

    @ManyToOne(fetch = FetchType.EAGER)
    public Answer getSelectedAnswer() {
        return selectedAnswer;
    }

    public void setSelectedAnswer(Answer selectedAnswer) {
        this.selectedAnswer = selectedAnswer;
    }


    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Column(name = "is_correct")
    public boolean getIsCorrect() {
        return this.isCorrect;
    }
}

