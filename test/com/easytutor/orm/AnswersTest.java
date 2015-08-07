package com.easytutor.orm;

import com.easytutor.dao.impl.AnswerDAOImpl;
import com.easytutor.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by root on 07.08.15.
 */
public class AnswersTest {
    @Test
    public void getAnswerTest(){
        AnswerDAOImpl answerDAO = new AnswerDAOImpl();
        answerDAO.setSessionFactory(HibernateUtil.getSessionFactory());


        answerDAO.getAnswerByInfo("Модуль 1", "Програмування інтернет", "Beб-caйт – цe", Optional.of(3), Optional.of("СП"));
    }
}
