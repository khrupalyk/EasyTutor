package com.easytutor.dao.impl;

import com.easytutor.dao.QuestionDAO;
import com.easytutor.models.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

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
}
