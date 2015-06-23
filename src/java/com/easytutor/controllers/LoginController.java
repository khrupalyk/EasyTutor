package com.easytutor.controllers;

import com.easytutor.orm.entities.*;
import com.easytutor.utils.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by root on 17.06.15.
 */
@Controller
public class LoginController {

    @RequestMapping({"/"})
    public String goHome() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
//        Test test = new Test();
//        test.setName("test name");
//        test.setTestId(UUID.randomUUID());
//
//        Question question = new Question();
//        question.setName("Questin");
//        question.setQuestionId(44);
//
//        List<Question> questions = new ArrayList<>();
//        questions.add(question);
//
//        test.setQuestions(questions);
//        question.getTests().add(test);
//        session.save(question);

        //--------------------------------------------

        Question question = (Question)session.get(Question.class, 2);

        question.getAnswers().forEach(e ->{
            Logger.getLogger("wadawdawd").info(e.toString());
        });

//        question.getQuestionsAnswers()
//
//        UserAtutor userAtutor = new UserAtutor();
//        userAtutor.setUserId(1);
////        session.save(userAtutor);
//
//        Question question1 = new Question();
//        question1.setName("name");
//        question1.setHeader("header");
//        question1.setQuestionId(2);
//
//        Answer answer = new Answer();
//        answer.setContent("answer content");
//        answer.setAnswerId(3);
//
//        Answer selectedAnswer = new Answer();
//        selectedAnswer.setContent("selected answer content");
//        selectedAnswer.setAnswerId(4);
//        selectedAnswer.getQuestions().add(question1);
//
//        question1.getAnswers().add(selectedAnswer);
//        question1.getAnswers().add(answer);
//
//        session.save(question1);
//        session.save(selectedAnswer);
//        session.save(question1);
//        session.save(userAtutor);

//        QuestionsAnswers questionsAnswers = new QuestionsAnswers();
//        questionsAnswers.setQuestion(question1);
//        questionsAnswers.setAnswer(answer);
//        questionsAnswers.setUserAtutor(userAtutor);
//        questionsAnswers.setSelectedAnswer(selectedAnswer);
//
//        answer.getQuestionsAnswers().add(questionsAnswers);

//        session.save(answer);

        session.getTransaction().commit();
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "login")
    public String login(@RequestParam("login") String login, @RequestParam("password") String pass) {
        Logger.getLogger(LoginController.class.getName()).info("Login and pass: " + login + " pass " + pass);
        return "index";
    }
}
