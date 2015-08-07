package com.easytutor.dao;

import com.easytutor.models.Question;
import com.easytutor.models.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by Andrii on 6/25/15.
 */
public interface QuestionDAO {
    void saveOrUpdate(Question question);

    void storeQuestion(Question question);

    void updateQuestion(Question question);

    void deleteQuestion(Question question);

    List<Question> getAllQuestions();

    List<Question> getQuestionsByTestInfo(Map<String, Object> params);

    List<Question> getQuestionsWithStatistic(List<Test> tests);

}
