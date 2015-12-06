package com.easytutor.dao.impl;

import com.easytutor.dao.UserDAO;
import com.easytutor.models.User;
import com.easytutor.models.UserRole;
import com.easytutor.utils.UsersRoles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.Collection;
import java.util.List;

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
        User user = (User) session.get(User.class, name);
        session.close();
        return user;
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        UserRole userRole = new UserRole();
        userRole.setRole(UsersRoles.USER.getRole());
        userRole.setUser(user);
        session.save(userRole);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean isUserExistWithSuchName(String name) {
        Session session = sessionFactory.openSession();
        boolean exist = session.createCriteria(User.class)
                .add(Restrictions.eq("username", name))
                .setProjection(Projections.property("username"))
                .uniqueResult() != null;
        session.close();
        return exist;
    }

    @Override
    public Collection<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        List<User> list = session.createCriteria(User.class).list();
        session.close();
        return list;
    }
}
