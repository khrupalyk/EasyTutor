package com.easytutor.dao;

import com.easytutor.models.Test;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;
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

    Test getTest(UUID testId, Session session);


    List<Test> getUniqueTests();

    List<Test> getTests(Map<String, Object> params);

    Test getTestWithQuestionStatistic(UUID testId);

}
