package com.easytutor.dao.impl;

import com.easytutor.dao.TestResultDAO;
import com.easytutor.models.TestResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by root on 29.06.15.
 */
public class TestResultDAOImpl implements TestResultDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(TestResult testResult) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(testResult);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void storeTestResult(TestResult testResult) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(testResult);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateTestResult(TestResult testResult) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(testResult);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteTestResult(TestResult testResult) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(testResult);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<TestResult> getAllTestResults() {
        Session session = sessionFactory.openSession();
        List<TestResult> testList = session.createQuery("from TestResult").list();
        session.close();
        return testList;
    }
}
