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
    private boolean correctExist = false;
    private String newCorrectAnswer = "";

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

    @Column(name = "exist_correct")
    public boolean getCorrectExist() {
        return correctExist;
    }

    public void setCorrectExist(boolean correctExist) {
        this.correctExist = correctExist;
    }

    @Column(name = "new_correct_answer")
    public String getNewCorrectAnswer() {
        return newCorrectAnswer;
    }

    public void setNewCorrectAnswer(String newCorrectAnswer) {
        this.newCorrectAnswer = newCorrectAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestsQuestionsId that = (TestsQuestionsId) o;

        if (isCorrect != that.isCorrect) return false;
        if (correctExist != that.correctExist) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (test != null ? !test.equals(that.test) : that.test != null) return false;
        if (selectedAnswer != null ? !selectedAnswer.equals(that.selectedAnswer) : that.selectedAnswer != null)
            return false;
        return !(newCorrectAnswer != null ? !newCorrectAnswer.equals(that.newCorrectAnswer) : that.newCorrectAnswer != null);

    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (test != null ? test.hashCode() : 0);
        result = 31 * result + (selectedAnswer != null ? selectedAnswer.hashCode() : 0);
        result = 31 * result + (isCorrect ? 1 : 0);
        result = 31 * result + (correctExist ? 1 : 0);
        result = 31 * result + (newCorrectAnswer != null ? newCorrectAnswer.hashCode() : 0);
        return result;
    }
}

