package com.easytutor.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
public class Question implements java.io.Serializable {

    //    private Integer questionId;
    private String name;
    private String header;
    private List<QuestionsAnswers> questionsAnswers = new ArrayList<>();
    private List<TestsQuestions> testsQuestions = new ArrayList<>();
    private List<Answer> answers = new ArrayList<>();

    public Question() {
    }

    public Question(String name, String header) {
        this.name = name;
        this.header = header;
    }

    @Transient
    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    //    @Id
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
//    @Column(name = "question_id", unique = true, nullable = false)
//    public Integer getQuestionId() {
//        return questionId;
//    }
//
//    public void setQuestionId(Integer questionId) {
//        this.questionId = questionId;
//    }

    @Column(name = "header")
    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @Id
    @Column(name = "name", unique = true, nullable = false, length = 20)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    @JoinTable(name = "questions_answers",
//            joinColumns = {
//                    @JoinColumn(name = "question_name", nullable = false, updatable = false)
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(name = "answer_content", nullable = false, updatable = false)
//            })
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "pk.question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<QuestionsAnswers> getQuestionsAnswers() {
        return questionsAnswers;
    }

    public void setQuestionsAnswers(List<QuestionsAnswers> answers) {
        this.questionsAnswers = answers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.question")
    public List<TestsQuestions> getTestsQuestions() {
        return testsQuestions;
    }

    public void setTestsQuestions(List<TestsQuestions> testsQuestions) {
        this.testsQuestions = testsQuestions;
    }

    @Override
    public String toString() {
        return "Question{" +
//                "questionId=" + questionId +
                ", name='" + name + '\'' +
                ", header='" + header + '\'' +
                '}';
    }
}