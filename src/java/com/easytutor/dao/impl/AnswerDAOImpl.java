package com.easytutor.dao.impl;

import com.easytutor.dao.AnswerDAO;
import com.easytutor.models.Answer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by root on 27.06.15.
 */
public class AnswerDAOImpl implements AnswerDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Answer answer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(answer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void storeAnswer(Answer answer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(answer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateAnswer(Answer answer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(answer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAnswer(Answer answer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(answer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Answer> getAllAnswers() {
        Session session = sessionFactory.openSession();
        List<Answer> answers = session.createQuery("from Answer").list();
        session.close();
        return answers;
    }
}
