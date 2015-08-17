package com.easytutor.dao.impl;

import com.easytutor.dao.AnswerDAO;
import com.easytutor.dao.ProposedAnswerDAO;
import com.easytutor.models.ProposedAnswer;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Distinct;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import javax.persistence.OrderBy;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 16.08.15.
 */
public class ProposedAnswerDAOImpl implements ProposedAnswerDAO {

    private SessionFactory sessionFactory;

    private AnswerDAO answerDAO;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public AnswerDAO getAnswerDAO() {
        return answerDAO;
    }

    public void setAnswerDAO(AnswerDAO answerDAO) {
        this.answerDAO = answerDAO;
    }

    @Override
    public void addProposedAnswer(ProposedAnswer proposedAnswer) {
        proposedAnswer.setSubmissionTime(new Date());
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(proposedAnswer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void acceptProposedAnswer(ProposedAnswer proposedAnswer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        answerDAO.setCorrectAnswer(proposedAnswer.getTest().getTestId(), proposedAnswer.getQuestion().getName(), proposedAnswer.getAnswer().getContent());
        session.delete(proposedAnswer);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void rejectProposedAnswer(ProposedAnswer proposedAnswer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(proposedAnswer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<ProposedAnswer> getAllProposedAnswers() {
        Session session = sessionFactory.openSession();
        List<ProposedAnswer> proposedAnswers = session.createCriteria(ProposedAnswer.class)
                .addOrder(Order.asc("submissionTime"))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        session.close();
        return proposedAnswers;
    }
}
