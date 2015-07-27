package com.easytutor.dao.impl;

import com.easytutor.dao.QuestionDAO;
import com.easytutor.models.Question;
import com.easytutor.models.QuestionsAnswers;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by root on 27.06.15.
 */
public class QuestionDAOImpl implements QuestionDAO {

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

    @Override
    public List<Question> getQuestionsByTestInfo(Map<String, Object> params) {
        Session session = sessionFactory.openSession();
        StringBuffer sb = new StringBuffer();
        params.forEach((k, v) -> {
            sb.append(k).append("=").append(" :").append(k).append(" AND ");
        });

        String txt = "select distinct q from Question q where q.id in (select tq.id.question.id from TestsQuestions tq where tq.pk.test.id in (select t.id from Test  t where " + sb.replace(sb.lastIndexOf("AND"), sb.lastIndexOf("AND") + 3, "" ).toString() + "))";
        System.out.println(txt);
        Query query = session.createQuery(txt);
        params.forEach(query::setParameter);
        List<Question> list = query.list();
        for (Question nextElement : list) {
            nextElement.setAnswers(nextElement.getQuestionsAnswers().stream().map(QuestionsAnswers::getAnswer).distinct().collect(Collectors.toList()));
        }
        session.close();
        return list;
    }
}
