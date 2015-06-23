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
    private List<Test> tests = new ArrayList<Test>(0);

    public Question() {
    }

    public Question( String name) {
        this.name = name;
    }

    public Question(String name, List<Test> tests) {
        this.name = name;
        this.tests = tests;
    }

    @Id
//    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "question_id", unique = true, nullable = false)
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }


    @Column(name = "name", unique = true, nullable = false, length = 20)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tests_questions", joinColumns = {
            @JoinColumn(name = "question_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "test_id",
                    nullable = false, updatable = false)})
    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {

        this.tests = tests;
    }


}