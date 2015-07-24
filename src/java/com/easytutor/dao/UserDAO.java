package com.easytutor.dao;

import com.easytutor.models.User;

/**
 * Created by root on 23.07.15.
 */
public interface UserDAO {
    User getUserByName(String name);

    void addUser(User user);
}
