package com.easytutor.dao;

import com.easytutor.api.rest.obj.QuestionInfo;
import com.easytutor.api.rest.obj.TestInfo;
import com.easytutor.models.*;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by root on 27.06.15.
 */
public class ATutorDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private UserATutor getUserOrSaveByName(String name, Session session) {
        Query query = session.createQuery("from UserATutor as user where user.name = :name");
        query.setParameter("name", name);
        UserATutor userATutor = (UserATutor) query.uniqueResult();
        if (userATutor != null) {
            return userATutor;
        } else {
            UserATutor user = new UserATutor();
            user.setName(name);
            session.save(user);
            return user;
        }
    }

    private Answer getAnswerOrSave(Answer content, Session session) {

        Query query = session.createQuery("from Answer as ans where ans.content = :name");
        query.setParameter("name", content.getContent());
        Answer answer1 = (Answer) query.uniqueResult();
        if (answer1 != null) {
            return answer1;
        } else {
//            Answer a = new Answer(content.getContent());
            session.save(content);
            return content;
        }
    }

    public void saveTest(TestInfo testInfo) {

        Session session = sessionFactory.openSession();
        Test test = new Test();
        test.setName(testInfo.getModuleName());
        test.setTestId(UUID.fromString(testInfo.getTestId()));

        List<QuestionInfo> questions = testInfo.getBody();

        UserATutor user = getUserOrSaveByName(testInfo.getUser(), session);

        List<TestsQuestions> testsQuestions = new ArrayList<>();

        for (QuestionInfo question : questions) {
            Question questionObj = new Question(question.getQuestion(), question.getQuestionHeader());
            Answer selectedAnswer = new Answer(question.getAnswer());
            List<String> answers = question.getAnswers();

            session.saveOrUpdate(questionObj);

//            for (String answer : answers) {
//                Answer answerObj = getAnswerOrSave(new Answer(answer), session);
//                answerObj.getQuestions().add(questionObj);
//            }

            TestsQuestions testsQuestions1 = createTestQuestions(test, questionObj, user, selectedAnswer);
            testsQuestions.add(testsQuestions1);
            questionObj.getTestsQuestions().add(testsQuestions1);

        }

        test.setTestsQuestions(testsQuestions);

        session.saveOrUpdate(test);
        session.getTransaction().commit();

    }

    public boolean checkEntityExists(Session session, Class clazz, String identifierKey, Object identifierValue) {
        return session.createCriteria(clazz)
                .add(Restrictions.eq(identifierKey, identifierValue))
                .setProjection(Projections.property(identifierKey))
                .uniqueResult() != null;
    }

    private TestsQuestions createTestQuestions(Test test, Question question, UserATutor userAtutor, Answer answer) {
        TestsQuestions testsQuestions = new TestsQuestions();
        testsQuestions.setTest(test);
        testsQuestions.setQuestion(question);
        testsQuestions.setUserATutor(userAtutor);
        testsQuestions.setSelectedAnswer(answer);
        return testsQuestions;
    }
}
