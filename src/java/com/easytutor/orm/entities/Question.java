package com.easytutor.orm.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")})
public class Question implements java.io.Serializable {

    private Integer questionId;
    private String name;
    private List<Test> tests = new ArrayList<>(0);
    private String header;
    private List<Answer> answers = new ArrayList<>();

    @Id
    @Column(name = "question_id", unique = true, nullable = false)
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Column(name = "header")
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Column(name = "name", unique = true, nullable = false, length = 20)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JoinTable(name = "tests_questions",
            joinColumns = {
                    @JoinColumn(name = "question_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {@JoinColumn(name = "test_id", nullable = false, updatable = false)
            })
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @JoinTable(name = "questions_answers",
            joinColumns = {
                    @JoinColumn(name = "question_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "answer_id", nullable = false, updatable = false)
            })
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}