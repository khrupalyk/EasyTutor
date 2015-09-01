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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionsAnswersId that = (QuestionsAnswersId) o;

        if (answer != null ? !answer.equals(that.answer) : that.answer != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        return !(testId != null ? !testId.equals(that.testId) : that.testId != null);

    }

    @Override
    public int hashCode() {
        int result = answer != null ? answer.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (testId != null ? testId.hashCode() : 0);
        return result;
    }
}
