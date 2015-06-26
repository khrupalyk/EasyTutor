package com.easytutor.dao;

import com.easytutor.models.Question;

import java.util.List;

/**
 * Created by Andrii on 6/25/15.
 */
public interface QuestionDAO {
    void saveOrUpdate(Question question);

    void storeQuestion(Question question);

    void updateQuestion(Question question);

    void deleteQuestion(Question question);

    List<Question> getAllQuestions();
}
