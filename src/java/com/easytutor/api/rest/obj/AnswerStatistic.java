package com.easytutor.api.rest.obj;

import java.io.Serializable;

/**
 * Created by root on 07.08.15.
 */
public class AnswerStatistic implements Serializable {

    private String answerName;
    private int selectedCount;

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }

    @Override
    public String toString() {
        return "AnswerStatistic{" +
                "answerName='" + answerName + '\'' +
                ", selectedCount=" + selectedCount +
                '}';
    }
}