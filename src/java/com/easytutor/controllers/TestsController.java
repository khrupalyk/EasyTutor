package com.easytutor.controllers;

import com.easytutor.dao.QuestionDAO;
import com.easytutor.dao.TestDAO;
import com.easytutor.models.Test;
import com.easytutor.utils.ApplicationContextProvider;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.logging.Logger;

/**
 * Created by root on 04.07.15.
 */

@Controller
public class TestsController {

    TestDAO testDAO = (TestDAO) ApplicationContextProvider.getApplicationContext().getBean(TestDAO.class);
    QuestionDAO questionDAO = (QuestionDAO) ApplicationContextProvider.getApplicationContext().getBean(QuestionDAO.class);


    @Secured("hasRole('ROLE_USER')")
    @RequestMapping(value = "test/{testId}/questions")
    public ModelAndView getQuestions(@PathVariable("testId") String testId) {

        Test test = testDAO.getTestWithQuestionStatistic(UUID.fromString(testId));

        ModelAndView modelAndView = new ModelAndView("WEB-INF/pages/testQuestions");
        modelAndView.addObject("test", test);

        return modelAndView;
    }

    @RequestMapping(value = "tests")
    public String tests() {
        return "WEB-INF/pages/tests";
    }

    static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    String urlEncodeUTF8(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    urlEncodeUTF8(entry.getKey().toString()),
                    urlEncodeUTF8(entry.getValue().toString())
            ));
        }
        return sb.toString();
    }

    @RequestMapping(value = "search")
    public ModelAndView searchTests(@RequestParam Map<String, String> params) {
        ModelAndView modelAndView = new ModelAndView("WEB-INF/pages/groupTest");
        modelAndView.addObject("params", urlEncodeUTF8(params));
        return modelAndView;
    }

    public Map<String, Object> filterParams(Map<String, String> params) {

        Map<String, Object> map = new TreeMap<>();

        List<String> searchField = new ArrayList<>();
        searchField.add("name");
        searchField.add("group");
        searchField.add("course");
        searchField.add("discipline");

        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
            if (searchField.contains(stringStringEntry.getKey())) {
                if (stringStringEntry.getKey().equals("course"))
                    map.put(stringStringEntry.getKey(), Integer.parseInt(stringStringEntry.getValue()));
                else
                    map.put(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        return map;
    }

    public Map<String, Object> filterParam(Map<String, String> params) {

        Map<String, Object> map = new TreeMap<>();

        List<String> searchField = new ArrayList<>();
        searchField.add("name");
        searchField.add("group");
        searchField.add("course");
        searchField.add("discipline");

        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
            if (searchField.contains(stringStringEntry.getKey())) {
                if (stringStringEntry.getKey().equals("course"))
                    map.put(stringStringEntry.getKey(), Integer.parseInt(stringStringEntry.getValue()));
                else if (stringStringEntry.getKey().equals("group"))
                    map.put("group", stringStringEntry.getValue());
                else if (stringStringEntry.getKey().equals("discipline"))
                    map.put("discipline", stringStringEntry.getValue());
                else
                    map.put(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        return map;
    }

    @RequestMapping(value = {"group-test"}, method = RequestMethod.GET)
    public
    @ResponseBody
    String search(@RequestParam Map<String, String> params) {


        JSONArray ja = new JSONArray();
        for (Test test : testDAO.getTests(filterParams(params))) {
            JSONObject jo = new JSONObject();
            jo.put("id", test.getTestId());
            jo.put("name", test.getName());
            jo.put("discipline", test.getDiscipline());
            jo.put("group", test.getGroup());
            jo.put("course", test.getCourse());
            jo.put("user", test.getUserATutor() != null ? test.getUserATutor().getName() : "Unknown user");
            jo.put("date", test.getSubmissionTime());
            if (test.getTestResult() != null)
                jo.put("result", test.getTestResult().toString());
            ja.put(jo);
        }

//        ModelAndView modelAndView = new ModelAndView("pages/groupTest");
//        modelAndView.addObject("testsJson", ja.toString());
        return ja.toString();
    }

    @RequestMapping(value = "questions/_all")
    public ModelAndView getAllQuestions(@RequestParam Map<String, String> params) {
        ModelAndView modelAndView = new ModelAndView("WEB-INF/pages/allQuestions");

        Map<String, Object> par = filterParam(params);
        if (par.isEmpty())
            modelAndView.addObject("questions", new ArrayList<>());
        else
            modelAndView.addObject("questions", questionDAO.getQuestionsByTestInfo(par));

        return modelAndView;
    }
}
