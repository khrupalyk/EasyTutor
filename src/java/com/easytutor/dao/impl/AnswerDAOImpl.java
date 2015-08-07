package com.easytutor.dao.impl;

import com.easytutor.api.rest.obj.AnswerStatistic;
import com.easytutor.api.rest.obj.FoundAnswer;
import com.easytutor.dao.AnswerDAO;
import com.easytutor.models.Answer;
import com.easytutor.models.Question;
import com.easytutor.models.Test;
import com.easytutor.models.TestsQuestions;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import com.easytutor.api.rest.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by root on 27.06.15.
 */
public class AnswerDAOImpl implements AnswerDAO {

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Answer answer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(answer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void storeAnswer(Answer answer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(answer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateAnswer(Answer answer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(answer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteAnswer(Answer answer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(answer);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Answer> getAllAnswers() {
        Session session = sessionFactory.openSession();
        List<Answer> answers = session.createQuery("from Answer").list();
        session.close();
        return answers;
    }

    @Override
    public FoundAnswer getAnswerByInfo(String testName, String discipline, String questionName, Optional<Integer> courseOpt, Optional<String> groupOpt) {

        Session session = sessionFactory.openSession();

        Criteria testIdsCriteria = session.createCriteria(Test.class)
                .add(Restrictions.eq("name", testName))
                .add(Restrictions.eq("discipline", discipline))
                .setProjection(Projections.property("testId"));

        courseOpt.map(course -> testIdsCriteria.add(Restrictions.eq("course", course)));
        groupOpt.map(group -> testIdsCriteria.add(Restrictions.eq("group", group)));

        List<TestsQuestions> testsQuestions = session.createCriteria(TestsQuestions.class).add(Restrictions.in("pk.test.testId", testIdsCriteria.list())).add(Restrictions.eq("pk.question.name", questionName)).list();


        FoundAnswer foundAnswer = testsQuestions.stream().filter(e -> e.getPk().getIsCorrect()).findAny().map(testQuestions -> {

            FoundAnswer foundAnswer2 = new FoundAnswer();
            foundAnswer2.setIsCorrect(true);
            foundAnswer2.setCorrectAnswer(testQuestions.getQuestion().getName());

            return foundAnswer2;
        }).orElse(createStatisticForFoundAnswer(testsQuestions));

            System.out.println(foundAnswer);

        session.close();

        return foundAnswer;

    }

    private FoundAnswer createStatisticForFoundAnswer(List<TestsQuestions> testsQuestions){

        FoundAnswer foundAnswer = new FoundAnswer();
        foundAnswer.setIsCorrect(false);

        testsQuestions.stream().map(TestsQuestions::getSelectedAnswer).collect(Collectors.groupingBy(Answer::getContent)).forEach((key, value) -> {
            AnswerStatistic statistic = new AnswerStatistic();
            statistic.setAnswerName(key);
            statistic.setSelectedCount(value.size());
            foundAnswer.getAnswerStatistic().add(statistic);
        });

        return foundAnswer;
    }
}
