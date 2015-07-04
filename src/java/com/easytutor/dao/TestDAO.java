package com.easytutor.dao;

import com.easytutor.models.Test;

import java.util.List;
import java.util.UUID;

/**
 * Created by Andrii on 6/25/15.
 */
public interface TestDAO {

    void saveOrUpdate(Test test);

    void storeTest(Test test);

    void updateTest(Test test);

    void deleteTest(Test test);

    List<Test> getAllTests();

    Test getTest(UUID testId);

    List<Test> getUniqueTests();

}
