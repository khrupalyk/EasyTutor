package com.easytutor.dao.impl;

import com.easytutor.dao.ProposedAnswerDAO;
import com.easytutor.models.ProposedAnswer;
import org.hibernate.SessionFactory;

/**
 * Created by root on 16.08.15.
 */
public class ProposedAnswerDAOImpl implements ProposedAnswerDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addProposedAnswer(ProposedAnswer proposedAnswer) {

    }
}
