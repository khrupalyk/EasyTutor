package com.easytutor.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by root on 28.06.15.
 */

@Entity
@Table(name = "questions_answers")
@AssociationOverrides(
        {
                @AssociationOverride(name = "pk.answer", joinColumns = @JoinColumn(name = "answer_content")),
                @AssociationOverride(name = "pk.question", joinColumns = @JoinColumn(name = "question_name"))
        }
)

public class QuestionsAnswers implements Serializable {

    private QuestionsAnswersId pk = new QuestionsAnswersId();

    @EmbeddedId
    public QuestionsAnswersId getPk() {
        return pk;
    }

    public void setPk(QuestionsAnswersId pk) {
        this.pk = pk;
    }

    @Transient
    public Question getQuestion() {
        return pk.getQuestion();
    }

    public void setQuestion(Question question) {
        this.pk.setQuestion(question);
    }

    @Transient
    public Answer getAnswer() {
        return pk.getAnswer();
    }

    public void setAnswer(Answer answer) {
        this.pk.setAnswer(answer);
    }

    @Transient
    public UUID getTestId() {
        return pk.getTestId();
    }

    public void setTestId(UUID testId) {
        this.pk.setTestId(testId);
    }
}
