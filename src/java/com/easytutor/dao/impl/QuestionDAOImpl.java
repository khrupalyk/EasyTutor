package com.easytutor.dao.impl;

import com.easytutor.dao.QuestionDAO;
import com.easytutor.dao.TestDAO;
import com.easytutor.models.*;
import com.easytutor.utils.ApplicationContextProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by root on 27.06.15.
 */
public class QuestionDAOImpl implements QuestionDAO {

    TestDAO testDAO = ApplicationContextProvider.getApplicationContext().getBean(TestDAO.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Question question) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(question);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void storeQuestion(Question question) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(question);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateQuestion(Question question) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(question);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteQuestion(Question question) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(question);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Question> getAllQuestions() {
        Session session = sessionFactory.openSession();
        List<Question> questions = session.createQuery("from Question").list();
        session.close();
        return questions;
    }

    private List<Question> getQuestions(List<Test> tests) {

        List<Question> questions = new ArrayList<>();

        tests.forEach(test -> {
            test.getTestsQuestions().forEach(testsQuestions -> {
                Answer selectedAnswer = testsQuestions.getSelectedAnswer();
                Question question = testsQuestions.getQuestion();
                List<Answer> ans = question.getAnswers().stream().map(e -> {
                    Answer newAnsw = new Answer();
                    newAnsw.setContent(e.getContent());
                    if (e.getContent().equals(selectedAnswer.getContent())) {
                        newAnsw.incrementSelectedCount();
                    }
                    return newAnsw;
                }).collect(Collectors.toList());
                question.setAnswers(ans);
                questions.add(question);
            });
        });

        Map<String, List<Question>> map = questions.stream().collect(Collectors.groupingBy(Question::getName));

        List<Question> newQuestions = new ArrayList<>();
        for (Map.Entry<String, List<Question>> stringListEntry : map.entrySet()) {
            Question question = new Question();
            question.setName(stringListEntry.getKey());

            List<Answer> newAnswers = new ArrayList<>();

            stringListEntry.getValue()
                    .stream()
                    .flatMap(e -> e.getAnswers().stream())
                    .collect(Collectors.groupingBy(Answer::getContent))
                    .forEach((answerName, answersList) -> {
                        Answer newAnswer = new Answer();
                        newAnswer.setContent(answerName);
                        newAnswer.setSelectedCount(answersList.stream().collect(Collectors.summingInt(Answer::getSelectedCount)));
                        newAnswers.add(newAnswer);
                    });

            question.setAnswers(newAnswers);
            newQuestions.add(question);
        }

        return newQuestions;
    }

    @Override
    public List<Question> getQuestionsByTestInfo(Map<String, Object> params) {
        Session session = sessionFactory.openSession();
        StringBuffer sb = new StringBuffer();
        params.forEach((k, v) -> {
            if (v instanceof Integer)
                sb.append(k).append("=").append(v).append(" AND ");
            else
                sb.append(k).append("=").append("\"").append(v).append("\" AND ");
        });

        List<Test> testList = session.createSQLQuery("SELECT * FROM tests WHERE " + sb.replace(sb.lastIndexOf("AND"), sb.lastIndexOf("AND") + 3, "").toString()).addEntity(Test.class).list();
        List<Question> questionsNew = getQuestions(testList.stream().map(test -> testDAO.getTest(test.getTestId())).collect(Collectors.toList()));

        session.close();
        return questionsNew;
    }
}
