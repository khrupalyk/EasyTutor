package com.easytutor.controllers;

import com.easytutor.orm.entities.Question;
import com.easytutor.orm.entities.Test;
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
        Test test = new Test();
        test.setName("test name");
        test.setTestId(44);

        Question question = new Question();
        question.setName("Questin");
        question.setQuestionId(44);

        List<Question> questions = new ArrayList<>();
        questions.add(question);

        test.setQuestions(questions);
        question.getTests().add(test);
        session.save(question);
        session.getTransaction().commit();
        return "index";
    }

    @RequestMapping(method = RequestMethod.POST, value = "login")
    public String login(@RequestParam("login") String login, @RequestParam("password") String pass) {
        Logger.getLogger(LoginController.class.getName()).info("Login and pass: " + login + " pass " + pass);
        return "index";
    }
}
