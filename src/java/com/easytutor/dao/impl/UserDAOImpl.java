package com.easytutor.dao.impl;

import com.easytutor.dao.UserDAO;
import com.easytutor.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by root on 23.07.15.
 */
public class UserDAOImpl implements UserDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        User user = (User)session.get(User.class, name);
        session.close();
        return user;
    }
}
