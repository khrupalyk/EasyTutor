package com.easytutor.orm.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by root on 24.06.15.
 */

@Entity
@Table(name = "tests_questions")
@AssociationOverrides(
        {
                @AssociationOverride(name = "pk.selectedAnswer", joinColumns = @JoinColumn(name = "answer_id")),
                @AssociationOverride(name = "pk.question", joinColumns = @JoinColumn(name = "question_id")),
                @AssociationOverride(name = "pk.test", joinColumns = @JoinColumn(name = "test_id")),
                @AssociationOverride(name = "pk.userATutor", joinColumns = @JoinColumn(name = "user_atutor_id"))

        }
)
public class TestsQuestions implements Serializable {

    private TestsQuestionsId pk = new TestsQuestionsId();

    @EmbeddedId
    public TestsQuestionsId getPk() {
        return pk;
    }

    public void setPk(TestsQuestionsId pk) {
        this.pk = pk;
    }

    @Transient
    public Question getQuestion(){
        return pk.getQuestion();
    }

    @Transient
    public Answer getSelectedAnswer(){
        return pk.getSelectedAnswer();
    }

    @Transient
    public Test getTest(){
        return pk.getTest();
    }

    @Transient
    public UserATutor getUserATutor(){
        return pk.getUserATutor();
    }

    public void setUserATutor(UserATutor userAtutor) {
        pk.setUserATutor(userAtutor);
    }

    public void setSelectedAnswer(Answer answer) {
        pk.setSelectedAnswer(answer);
    }

    public void setTest(Test test) {
        pk.setTest(test);
    }

    public void setQuestion(Question question) {
        pk.setQuestion(question);
    }
}
