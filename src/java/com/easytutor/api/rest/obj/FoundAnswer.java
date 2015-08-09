package com.easytutor.api.rest.obj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 07.08.15.
 */
public class FoundAnswer implements Serializable {

    private boolean isCorrect = false;
    private String correctAnswer;
    private List<AnswerStatistic> answerStatistic = new ArrayList<>();
    private boolean exist;

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<AnswerStatistic> getAnswerStatistic() {
        return answerStatistic;
    }

    public void setAnswerStatistic(List<AnswerStatistic> answerStatistic) {
        this.answerStatistic = answerStatistic;
    }

    public boolean isExist() {
        return exist;
    }

    public void setExist(boolean exist) {
        this.exist = exist;
    }

    @Override
    public String toString() {
        return "FoundAnswer{" +
                "isCorrect=" + isCorrect +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", answerStatistic=" + answerStatistic +
                '}';
    }
}
