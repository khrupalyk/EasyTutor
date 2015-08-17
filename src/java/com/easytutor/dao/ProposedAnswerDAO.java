package com.easytutor.dao;

import com.easytutor.models.ProposedAnswer;

import java.util.List;

/**
 * Created by root on 16.08.15.
 */
public interface ProposedAnswerDAO {
    void addProposedAnswer(ProposedAnswer proposedAnswer);
    void acceptProposedAnswer(ProposedAnswer proposedAnswer);
    void rejectProposedAnswer(ProposedAnswer proposedAnswer);
    List<ProposedAnswer> getAllProposedAnswers();
}
