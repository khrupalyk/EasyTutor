package com.easytutor.orm;

import com.easytutor.orm.entities.Question;
import com.easytutor.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by root on 22.06.15.
 */
public class TestsMappingTest {

    @Test
    public void simple(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        com.easytutor.orm.entities.Test test = new com.easytutor.orm.entities.Test();
        test.setName("test name");
        test.setTestId(UUID.randomUUID());

        Question question = new Question();
        question.setName("Questin");
        question.setQuestionId(44);

        List<Question> questions = new ArrayList<>();
        questions.add(question);

        test.setQuestions(questions);
        question.getTests().add(test);
        session.save(question);
        session.getTransaction().commit();

    }
}
