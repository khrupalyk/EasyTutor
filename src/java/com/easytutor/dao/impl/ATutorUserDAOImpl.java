package com.easytutor.dao.impl;

import com.easytutor.dao.UserATutorDAO;
import com.easytutor.models.UserATutor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by root on 27.06.15.
 */
public class ATutorUserDAOImpl implements UserATutorDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(UserATutor userATutor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(userATutor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void storeUserATutor(UserATutor userATutor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(userATutor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateUserATutor(UserATutor userATutor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(userATutor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteUserATutor(UserATutor userATutor) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(userATutor);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<UserATutor> getAllATutorUsers() {
        Session session = sessionFactory.openSession();
        List<UserATutor> users = session.createQuery("from UserATutor ").list();
        session.close();
        return users;
    }

    @Override
    public UserATutor getUserOrSaveByName(String name) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from UserATutor as user where user.name = :name");
        query.setParameter("name", name);
        UserATutor userATutor = (UserATutor) query.uniqueResult();
        if (userATutor != null) {
            return userATutor;
        } else {
            UserATutor user = new UserATutor();
            user.setName(name);
            session.save(user);
            return user;
        }
    }
}
