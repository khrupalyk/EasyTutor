package com.easytutor.api.rest.obj;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by root on 20.06.15.
 */
public class TestInfo implements Serializable {
    private String user;
    private String group;
    private String discipline;
    private String moduleName;
    private String testId;
    private ArrayList<QuestionInfo> body;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public ArrayList<QuestionInfo> getBody() {
        return body;
    }

    public void setBody(ArrayList<QuestionInfo> body) {
        this.body = body;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    @Override
    public String toString() {
        return "TestInfo{" +
                "user='" + user + '\'' +
                ", group='" + group + '\'' +
                ", discipline='" + discipline + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", testId='" + testId + '\'' +
                ", body=" + body +
                '}';
    }
}
