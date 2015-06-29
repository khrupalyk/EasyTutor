package com.easytutor.dao;

import com.easytutor.models.TestResult;
import com.easytutor.models.UserATutor;

import java.util.List;

/**
 * Created by root on 29.06.15.
 */
public interface TestResultDAO {
        void saveOrUpdate(TestResult testResult);

        void storeTestResult(TestResult testResult);

        void updateTestResult(TestResult testResult);

        void deleteTestResult(TestResult testResult);

        List<TestResult> getAllTestResults();

    }
