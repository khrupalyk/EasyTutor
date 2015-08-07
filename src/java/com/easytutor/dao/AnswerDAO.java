package com.easytutor.dao;

import com.easytutor.api.rest.obj.FoundAnswer;
import com.easytutor.models.Answer;
import com.easytutor.models.UserATutor;

import java.util.List;
import java.util.Optional;

/**
 * Created by Andrii on 6/25/15.
 */
public interface AnswerDAO {
    void saveOrUpdate(Answer answer);

    void storeAnswer(Answer answer);

    void updateAnswer(Answer answer);

    void deleteAnswer(Answer answer);

    List<Answer> getAllAnswers();

    FoundAnswer getAnswerByInfo(String testName, String discipline, String questionName, Optional<Integer> course, Optional<String> group);
}
