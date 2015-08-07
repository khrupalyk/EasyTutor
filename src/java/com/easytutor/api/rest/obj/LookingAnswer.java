package com.easytutor.api.rest.obj;

import java.io.Serializable;

/**
 * Created by root on 06.08.15.
 */
public class LookingAnswer implements Serializable {
    private String testName;
    private String discipline;
    private String group;
    private String questionName;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }
}
