package com.easytutor.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by root on 28.06.15.
 */

@Embeddable
public class QuestionsAnswersId implements Serializable {
    private Answer answer;
    private Question question;
    private UUID testId;

    @Column(name = "test_id", columnDefinition = "BINARY(16)")
    public UUID getTestId() {
        return testId;
    }

    public void setTestId(UUID testId) {
        this.testId = testId;
    }

    @ManyToOne
    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @ManyToOne
    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
