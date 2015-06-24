package com.easytutor.orm;

import com.easytutor.orm.entities.Answer;
import com.easytutor.orm.entities.Question;
import com.easytutor.orm.entities.TestsQuestions;
import com.easytutor.orm.entities.UserATutor;
import com.easytutor.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
//import

/**
 * Created by root on 22.06.15.
 */
public class TestsMappingTest {

    Session session = HibernateUtil.getSessionFactory().openSession();

    @Before
    public void beforeAll() throws IOException {
        String text = new String(Files.readAllBytes(Paths.get("db/scheme.sql")), StandardCharsets.UTF_8);
        String queries[] = text.split(";");
        for (String query : queries) {
            if (!query.trim().isEmpty())
                session.createSQLQuery(query.replace("\n", "")).executeUpdate();
        }
    }

    @Test
    public void storeTestQuestionSelectedAnswerTest() {

        session.beginTransaction();

        com.easytutor.orm.entities.Test test = new com.easytutor.orm.entities.Test();

        test.setName("test name");
        test.setTestId(UUID.randomUUID());

        Question question = new Question();
        question.setName("Question1");
        Question questionNext = new Question();
        questionNext.setName("Question2");
        session.save(question);
        session.save(questionNext);

        UserATutor userAtutor = new UserATutor();
        userAtutor.setName("Khrupalik");
        session.save(userAtutor);

        Answer answer = new Answer();
        answer.setContent("answer");
        answer.getQuestions().add(question);
        session.save(answer);

        Answer answerNext = new Answer();
        answerNext.setContent("answer next");
        answerNext.getQuestions().add(questionNext);
        session.save(answerNext);

        TestsQuestions testsQuestions = createTestQuestions(test, question, userAtutor, answer);
        TestsQuestions testsQuestionsNext = createTestQuestions(test, questionNext, userAtutor, answerNext);

        test.getTestsQuestions().add(testsQuestions);
        test.getTestsQuestions().add(testsQuestionsNext);

        question.getTestsQuestions().add(testsQuestions);
        question.getTestsQuestions().add(testsQuestionsNext);

        session.save(test);

        UserATutor userATutorFromDB = (UserATutor) session.get(UserATutor.class, userAtutor.getUserId());

        assertEquals("ATutor user name not equals!", userATutorFromDB.getName(), userAtutor.getName());

        com.easytutor.orm.entities.Test testFromDB = (com.easytutor.orm.entities.Test) session.get(com.easytutor.orm.entities.Test.class, test.getTestId());

        assertEquals("Test name not equals!", test.getName(), testFromDB.getName());

        assertEquals("Size question in test not equals!", 2, testFromDB.getTestsQuestions().size());

        testFromDB.getTestsQuestions().forEach(
                e -> assertEquals("User in test not equals!", userAtutor, e.getUserATutor())
        );

        assertEquals("Selected answer not equals!", testFromDB.getTestsQuestions().get(0).getSelectedAnswer(), answer);

        assertEquals("Selected answer not equals!", testFromDB.getTestsQuestions().get(1).getSelectedAnswer(), answerNext);

        testFromDB.getTestsQuestions().forEach(
                e -> System.out.println("Selected answer " + e.getSelectedAnswer() + " for question " + e.getQuestion())
        );

        session.getTransaction().commit();

    }

    public TestsQuestions createTestQuestions(com.easytutor.orm.entities.Test test, Question question, UserATutor userAtutor, Answer answer) {
        TestsQuestions testsQuestions = new TestsQuestions();
        testsQuestions.setTest(test);
        testsQuestions.setQuestion(question);
        testsQuestions.setUserATutor(userAtutor);
        testsQuestions.setSelectedAnswer(answer);
        return testsQuestions;
    }
}
