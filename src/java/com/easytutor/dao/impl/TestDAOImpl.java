package com.easytutor.dao.impl;

import com.easytutor.dao.TestDAO;
import com.easytutor.models.Test;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by root on 26.06.15.
 */
public class TestDAOImpl implements TestDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Test test) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(test);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void storeTest(Test test) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(test);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateTest(Test test) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(test);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteTest(Test test) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(test);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Test> getAllTests() {
        Session session = sessionFactory.openSession();
        List<Test> testList = session.createQuery("from Test").list();
        session.close();
        return testList;
    }
}
