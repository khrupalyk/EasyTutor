package com.easytutor.dao.impl;

import com.easytutor.api.rest.obj.AnswerStatistic;
import com.easytutor.api.rest.obj.FoundAnswer;
import com.easytutor.dao.AnswerDAO;
import com.easytutor.models.Answer;
import com.easytutor.models.Question;
import com.easytutor.models.Test;
import com.easytutor.models.TestsQuestions;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import com.easytutor.api.rest.*;
import org.hibernate.engine.spi.LoadQueryInfluencers;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.SessionImpl;
import org.hibernate.loader.OuterJoinLoader;
import org.hibernate.loader.criteria.CriteriaLoader;
import org.hibernate.persister.entity.OuterJoinLoadable;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
        try {
            Criteria testIdsCriteria = session.createCriteria(Test.class)
                    .add(Restrictions.eq("name", testName))
                    .add(Restrictions.eq("discipline", discipline))
                    .setProjection(Projections.property("testId"));

            courseOpt.map(course -> testIdsCriteria.add(Restrictions.eq("course", course)));
            groupOpt.map(group -> testIdsCriteria.add(Restrictions.eq("group", group)));

            List<TestsQuestions> testsQuestions = session.createCriteria(TestsQuestions.class).add(Restrictions.in("pk.test.testId", testIdsCriteria.list())).add(Restrictions.eq("pk.question.name", questionName)).list();

            FoundAnswer foundAnswer;

            if (testsQuestions.isEmpty()) {
                foundAnswer = new FoundAnswer();
                foundAnswer.setExist(false);
            } else {
                foundAnswer = testsQuestions.stream().filter(e -> e.getPk().getIsCorrect()).findAny().map(testQuestions -> {
                    FoundAnswer foundAnswer2 = new FoundAnswer();
                    foundAnswer2.setIsCorrect(true);
                    foundAnswer2.setCorrectAnswer(testQuestions.getQuestion().getName());

                    return foundAnswer2;
                }).orElse(createStatisticForFoundAnswer(testsQuestions));
                foundAnswer.setExist(true);
                System.out.println(foundAnswer);
            }


            session.close();

            return foundAnswer;
        } catch (Exception e) {
            session.close();
            return new FoundAnswer();
        }

    }

    @Override
    public List<FoundAnswer> getAnswersByInfo(String testName, String discipline, Optional<Integer> courseOpt, Optional<String> groupOpt, List<String> questions) {

        Session session = sessionFactory.openSession();
        try {
            Criteria testIdsCriteria = session.createCriteria(Test.class)
                    .add(Restrictions.eq("name", testName))
                    .add(Restrictions.eq("discipline", discipline))
                    .setProjection(Projections.property("testId"));

            courseOpt.map(course -> testIdsCriteria.add(Restrictions.eq("course", course)));
            groupOpt.map(group -> testIdsCriteria.add(Restrictions.eq("group", group)));

            List<FoundAnswer> foundAnswers = questions.stream().map(question -> {

                List<TestsQuestions> testsQuestions = session.createCriteria(TestsQuestions.class).add(Restrictions.in("pk.test.testId", testIdsCriteria.list())).add(Restrictions.eq("pk.question.name", question)).list();

                FoundAnswer foundAnswer;

                if (testsQuestions.isEmpty()) {
                    foundAnswer = new FoundAnswer();
                    foundAnswer.setQuestion(question);
                    foundAnswer.setExist(false);
                } else {

                    foundAnswer = testsQuestions.stream().filter(e -> e.getPk().getIsCorrect() || e.getPk().getCorrectExist()).findAny().map(testQuestions -> {
                        FoundAnswer foundAnswer2 = new FoundAnswer();
                        foundAnswer2.setQuestion(question);
                        foundAnswer2.setIsCorrect(true);
                        if (testQuestions.isCorrect())
                            foundAnswer2.setCorrectAnswer(testQuestions.getSelectedAnswer().getContent());
                        else foundAnswer2.setCorrectAnswer(testQuestions.getPk().getNewCorrectAnswer());

                        foundAnswer2.setAnswerStatistic(createStatisticForFoundAnswer(testsQuestions).getAnswerStatistic());
                        return foundAnswer2;
                    }).orElse(createStatisticForFoundAnswer(testsQuestions));
                    foundAnswer.setExist(true);
                    foundAnswer.setQuestion(question);
                    System.out.println(foundAnswer);
                }
                return foundAnswer;
            }).collect(Collectors.toList());


            session.close();

            return foundAnswers;
        } catch (Exception e) {
            session.close();
            return new ArrayList<>();
        }

    }

    private FoundAnswer createStatisticForFoundAnswer(List<TestsQuestions> testsQuestions) {

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

    public void setCorrectAnswer(UUID testId, String question, String answer) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query selectedCurrentQuery = session.createQuery("select tq.id.selectedAnswer.content from TestsQuestions as tq where tq.id.test.id = :testId and tq.id.question.name = :question ");
        selectedCurrentQuery.setParameter("testId", testId);
        selectedCurrentQuery.setParameter("question", question.trim());
        String currentSelected = (String) selectedCurrentQuery.uniqueResult();

        System.out.println("Current selected: " + currentSelected);

        Test test = (Test) session.createQuery("from Test as test where test.id = :testId").setParameter("testId", testId).uniqueResult();

        Query testsIdsQuery = session.createQuery("select test.id from Test as test where test.name = :test and test.discipline = :discipline and test.course = :course and test.group = :group");
        testsIdsQuery.setParameter("discipline", test.getDiscipline());
        testsIdsQuery.setParameter("test", test.getName());
        testsIdsQuery.setParameter("group", test.getGroup());
        testsIdsQuery.setParameter("course", test.getCourse());

        List<UUID> testIds = testsIdsQuery.list();

        Query query = session.createQuery("update TestsQuestions as tq set tq.id.correctExist = true, tq.id.newCorrectAnswer = :newCorrect where tq.id.test.id in (:testId) and tq.id.question.name = :question");
        query.setParameter("question", question.trim());
        query.setParameterList("testId", testIds);
        query.setParameter("newCorrect", answer);
        int countUpdateRow = query.executeUpdate();
        System.out.println("Update row2: " + countUpdateRow + " question: " + question + ", answer = " + answer.trim() + " test id: " + testId);

        Query setFalse = session.createQuery("update TestsQuestions as tq set tq.id.isCorrect = false where tq.id.test.id in (:testId) and tq.id.question.name = :question ");

        setFalse.setParameter("question", question.trim());
        setFalse.setParameterList("testId", testIds);
        setFalse.executeUpdate();

        Query setFalseExist = session.createQuery("update TestsQuestions as tq set tq.id.correctExist = false where tq.id.test.id in (:testId) and tq.id.question.name = :question and tq.id.newCorrectAnswer != :answer");

        setFalseExist.setParameter("answer", answer.trim());
        setFalseExist.setParameter("question", question.trim());
        setFalseExist.setParameterList("testId", testIds);
        setFalseExist.executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

}
