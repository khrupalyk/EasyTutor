package com.easytutor.dao;

import com.easytutor.models.Question;
import com.easytutor.models.UserATutor;

import java.util.List;

/**
 * Created by Andrii on 6/25/15.
 */
public interface UserATutorDAO {
    void saveOrUpdate(UserATutor userATutor);

    void storeUserATutor(UserATutor userATutor);

    void updateUserATutor(UserATutor userATutor);

    void deleteUserATutor(UserATutor userATutor);

    List<UserATutor> getAllATutorUsers();
}
