package com.easytutor.dao;

import com.easytutor.models.User;

import java.util.Collection;

/**
 * Created by root on 23.07.15.
 */
public interface UserDAO {
    User getUserByName(String name);

    void addUser(User user);

    boolean isUserExistWithSuchName(String name);

    Collection<User> getAllUsers();
}
