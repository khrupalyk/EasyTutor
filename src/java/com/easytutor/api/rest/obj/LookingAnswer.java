package com.easytutor.api.rest.obj;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 06.08.15.
 */
public class LookingAnswer implements Serializable {
    private String testName;
    private String discipline;
    private String group;
    private List<String> questions;

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

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }
}
