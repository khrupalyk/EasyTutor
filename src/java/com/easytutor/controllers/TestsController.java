package com.easytutor.controllers;

import com.easytutor.dao.TestDAO;
import com.easytutor.models.Test;
import com.easytutor.utils.ApplicationContextProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

/**
 * Created by root on 04.07.15.
 */

@Controller
public class TestsController {

    TestDAO testDAO = (TestDAO) ApplicationContextProvider.getApplicationContext().getBean(TestDAO.class);


    @RequestMapping(value = "test/{testId}/questions")
    public ModelAndView getQuestions(@PathVariable("testId") String testId) {

        Test test = testDAO.getTest(UUID.fromString(testId));

        ModelAndView modelAndView = new ModelAndView("pages/testQuestions");
        modelAndView.addObject("test", test);

        return modelAndView;
    }

    @RequestMapping(value = "tests")
    public ModelAndView a() {
        JSONArray ja = new JSONArray();
        for (Test test : testDAO.getUniqueTests()) {
            JSONObject jo = new JSONObject();
            jo.put("testName", test.getName());
            jo.put("discipline", test.getDiscipline());
            jo.put("group", test.getGroup());
            jo.put("course", test.getCourse());
            jo.put("count", test.getTestCount());
            ja.put(jo);
        }

        ModelAndView modelAndView = new ModelAndView("pages/tests");
        modelAndView.addObject("testsJson", ja.toString());
        return modelAndView;
    }
}
