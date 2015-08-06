package com.easytutor.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 22.06.15.
 */

@Entity
@Table(name = "answers")
public class Answer implements Serializable {

//    private int answerId;
    private String content;
    private int selectedCount = 0;
    private List<QuestionsAnswers> questionsAnswers = new ArrayList<>();

    public Answer() {
    }

    public Answer(String content) {
        this.content = content;
    }


    //    @Id
//    @GeneratedValue(generator = "increment")
//    @GenericGenerator(name = "increment", strategy = "increment")
//    @Column(name = "answer_id")
//    public int getAnswerId() {
//        return answerId;
//    }
//
//    public void setAnswerId(int answerId) {
//        this.answerId = answerId;
//    }


    @Id
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToMany(mappedBy = "pk.answer", fetch = FetchType.LAZY)
    public List<QuestionsAnswers> getQuestionsAnswers() {
        return questionsAnswers;
    }

    public void setQuestionsAnswers(List<QuestionsAnswers> questionsAnswers) {
        this.questionsAnswers = questionsAnswers;
    }

    @Override
    public String toString() {
        return "Answer{" +
//                "answerId=" + answerId +
                ", content='" + content + '\'' +
                '}';
    }

    @Transient
    public int getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(int selectedCount) {
        this.selectedCount = selectedCount;
    }
    public void incrementSelectedCount() {
        this.selectedCount ++;
    }
}
