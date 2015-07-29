package com.easytutor.dao.impl;

import com.easytutor.dao.TestDAO;
import com.easytutor.models.Question;
import com.easytutor.models.Test;
import com.easytutor.models.TestsQuestions;
import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Override
    public Test getTest(UUID testId) {
        Session session = sessionFactory.openSession();
        Test test = (Test) session.get(Test.class, testId);
        Iterator<TestsQuestions> l = test.getTestsQuestions().iterator();
        while (l.hasNext()) {
            Question nextElement = l.next().getQuestion();
            Query query = session.createQuery("" +
                    "from Answer as answ where answ.id IN (select q.pk.answer.id from QuestionsAnswers as q where q.pk.testId = :testId AND q.pk.question.id = :questionId)");
            query.setParameter("testId", testId);
            query.setParameter("questionId", nextElement.getName());
            nextElement.setAnswers(query.list());
        }
        session.close();

        return test;
    }

    @Override
    public List<Test> getUniqueTests() {
        Session session = sessionFactory.openSession();
        List results = session.createCriteria(Test.class)
                .setProjection(Projections.projectionList()
                                .add(Projections.groupProperty("course"), "course")
                                .add(Projections.groupProperty("group"), "group")
                                .add(Projections.groupProperty("name"), "name")
                                .add(Projections.groupProperty("discipline"), "discipline")
                                .add(Projections.sqlProjection(
                                        "count(*) as count",
                                        new String[]{"count"},
                                        new Type[]{StandardBasicTypes.INTEGER}
                                ), "testCount")
                ).setResultTransformer(Transformers.aliasToBean(Test.class))
                .list();
        session.close();
        return results;
    }

    @Override
    public List<Test> getTests(Map<String, Object> params) {

        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Test.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);;
        for (Map.Entry<String, Object> stringStringEntry : params.entrySet()) {
            criteria.add(Restrictions.eq(stringStringEntry.getKey(), stringStringEntry.getValue()));
        }
        List result = criteria.list();
        session.close();
        return result;
    }
}
