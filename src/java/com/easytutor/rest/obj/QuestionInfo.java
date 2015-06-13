package com.easytutor.rest.obj;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.*;

/**
 * Created by root on 12.06.15.
 */
public class QuestionInfo implements Serializable {

    private String answer;
    private String user;
    private String scoreOnQuestion;
    private String questionHeader;
    private String question;
    private String moduleName;
    private String group;
    private String discipline;
    private List<String> answers;

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
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

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestionHeader() {
        return questionHeader;
    }

    public void setQuestionHeader(String questionHeader) {
        this.questionHeader = questionHeader;
    }

    public String getScoreOnQuestion() {
        return scoreOnQuestion;
    }

    public void setScoreOnQuestion(String scoreOnQuestion) {
        this.scoreOnQuestion = scoreOnQuestion;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "answer='" + answer + '\'' +
                ", user='" + user + '\'' +
                ", scoreOnQuestion='" + scoreOnQuestion + '\'' +
                ", questionHeader='" + questionHeader + '\'' +
                ", question='" + question + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", group='" + group + '\'' +
                ", discipline='" + discipline + '\'' +
                ", answers=" + answers +
                '}';
    }
}
