package com.easytutor.dao;

import com.easytutor.models.Test;

import java.util.List;

/**
 * Created by Andrii on 6/25/15.
 */
public interface TestDAO {

    Test getOrStore(Test test);

    void storeTest(Test test);

    void updateTest(Test test);

    void deleteTest(Test test);

    List<Test> getAllTests();

}
