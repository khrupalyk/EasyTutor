package com.easytutor.dao.impl;

import com.easytutor.dao.QuestionDAO;
import com.easytutor.dao.TestDAO;
import com.easytutor.models.*;
import com.easytutor.utils.ApplicationContextProvider;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by root on 27.06.15.
 */
public class QuestionDAOImpl implements QuestionDAO {

    TestDAO testDAO = ApplicationContextProvider.getApplicationContext().getBean(TestDAO.class);

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveOrUpdate(Question question) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(question);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void storeQuestion(Question question) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(question);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateQuestion(Question question) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(question);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteQuestion(Question question) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(question);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Question> getAllQuestions() {
        Session session = sessionFactory.openSession();
        List<Question> questions = session.createQuery("from Question").list();
        session.close();
        return questions;
    }

    List<Question> awd(List<Test> tests) {

        List<Question> questions = new ArrayList<>();

        tests.forEach(test -> {
            test.getTestsQuestions().forEach(testsQuestions -> {
                Answer selectedAnswer = testsQuestions.getSelectedAnswer();
                Question question = testsQuestions.getQuestion();
                question.getAnswers().forEach(e -> {
                    if (e.getContent().equals(selectedAnswer.getContent())) {
                        e.incrementSelectedCount();
                    }
                });
                questions.add(question);
            });
        });

        Map<String, List<Question>> map = questions.stream().collect(Collectors.groupingBy(Question::getName));

        List<Question> newQuestions = new ArrayList<>();
        for (Map.Entry<String, List<Question>> stringListEntry : map.entrySet()) {
            Question question = new Question();
            question.setName(stringListEntry.getKey());

            List<Answer> newAnswers = new ArrayList<>();

            stringListEntry.getValue()
                    .stream()
                    .flatMap(e -> {
                        System.out.println("Question: " + e.getName() + " have count answers" + e.getAnswers().size());
                        return e.getAnswers().stream();
                    })
                    .collect(Collectors.groupingBy(Answer::getContent))
                    .forEach((answerName, answersList) -> {
                        Answer newAnswer = new Answer();
                        System.out.println("           AnsweR name: "  + answerName + ", " + answersList.size());
                        newAnswer.setContent(answerName);
                        newAnswer.setSelectedCount(answersList.stream().collect(Collectors.summingInt(Answer::getSelectedCount)));
                        newAnswers.add(newAnswer);
                    });

            question.setAnswers(newAnswers);
            newQuestions.add(question);
        }

        return newQuestions;
    }

    @Override
    public List<Question> getQuestionsByTestInfo(Map<String, Object> params) {
        Session session = sessionFactory.openSession();
        StringBuffer sb = new StringBuffer();
        params.forEach((k, v) -> {
            if (v instanceof Integer)
                sb.append(k).append("=").append(v).append(" AND ");
            else
                sb.append(k).append("=").append("\"").append(v).append("\" AND ");
        });

//        DetachedCriteria selectTestIds = DetachedCriteria.forClass(Test.class);
//
//        params.forEach((k, v) -> selectTestIds.add(Restrictions.eq(k, v)));
//        selectTestIds.setProjection(Projections.property("testId"));

//        String txt = "from TestsQuestions tq where tq.pk.test.id in (select t.id from Test  t where " + sb.replace(sb.lastIndexOf("AND"), sb.lastIndexOf("AND") + 3, "").toString() + ")";
//        System.out.println(txt);
        //        Query querysession.createCriteria(TestsQuestions.class).add(Subqueries.in("testId", selectTestIds));

//        Query query = session.createQuery(txt);
//        params.forEach(query::setParameter);
//        List<TestsQuestions> list = query.list();
//        List<Question> questions = new ArrayList<>();
//
//        for (TestsQuestions nextElement : list) {
//            Question question = nextElement.getQuestion();
//            nextElement.getTestsQuestions().stream().map(TestsQuestions::getSelectedAnswer).forEach(System.out::println);
//            nextElement.setAnswers(nextElement.stream().map(QuestionsAnswers::getAnswer).distinct().collect(Collectors.toList()));
//            question.setAnswers(question.getQuestionsAnswers().stream().map(QuestionsAnswers::getAnswer).distinct().collect(Collectors.toList()));
//            questions.add(question);
//        }
//        Map<String, List<Question>> questionListMap = questions.stream().collect(Collectors.groupingBy(Question::getName));
//        System.out.println(sb.replace(sb.lastIndexOf("AND"), sb.lastIndexOf("AND") + 3, "").toString());
//        String selectTestIds = "SELECT test_id FROM tests WHERE " + sb.replace(sb.lastIndexOf("AND"), sb.lastIndexOf("AND") + 3, "").toString();
//        String query = " SELECT * FROM questions WHERE name IN (" +
//                "SELECT question_name FROM tests_questions WHERE test_id IN (" + selectTestIds + "))";
//        System.out.println(query);
//        List<Question> questionsNew = session.createSQLQuery(query).addEntity(Question.class).list();
//
//        questionsNew.forEach(question -> {
//            List<Answer> answers = session.createSQLQuery(("SELECT * FROM answers WHERE content IN (SELECT answer_content FROM questions_answers WHERE test_id IN(" + selectTestIds + ") AND question_name = \"" + question.getName() + "\")").replace(":","':'")).addEntity(Answer.class).list();
//            System.out.println(answers.size());
//            question.setAnswers(answers);
//        });
        List<Test> testList = session.createSQLQuery("SELECT * FROM tests WHERE " + sb.replace(sb.lastIndexOf("AND"), sb.lastIndexOf("AND") + 3, "").toString()).addEntity(Test.class).list();
        List<Question> questionsNew = awd(testList.stream().map(test -> testDAO.getTest(test.getTestId())).collect(Collectors.toList()));

//        questionListMap.forEach((k, questionList) -> {
//            Question question = questionList.get(0);
//            questionsNew.add(question);
//
//            for(Question question1 :questionList){
//                question1.getTestsQuestions().forEach(e -> {
//                    for(Answer a: question1.getAnswers()){
//                        if(a.getContent().equals(e.getSelectedAnswer().getContent())){
//                            a.incrementSelectedCount();
//                        }
//                    }
//                    question.setAnswers(question1.getAnswers());
//                });
//            }
//
//        });
        session.close();
        return questionsNew;
    }
}
