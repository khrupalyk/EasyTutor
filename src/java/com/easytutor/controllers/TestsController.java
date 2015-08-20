package com.easytutor.controllers;

import com.easytutor.dao.AnswerDAO;
import com.easytutor.dao.ProposedAnswerDAO;
import com.easytutor.dao.QuestionDAO;
import com.easytutor.dao.TestDAO;
import com.easytutor.models.Answer;
import com.easytutor.models.ProposedAnswer;
import com.easytutor.models.Question;
import com.easytutor.models.Test;
import com.easytutor.utils.ApplicationContextProvider;
import com.easytutor.utils.UsersRoles;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by root on 04.07.15.
 */

@Controller
public class TestsController {

    TestDAO testDAO = ApplicationContextProvider.getApplicationContext().getBean(TestDAO.class);
    QuestionDAO questionDAO = ApplicationContextProvider.getApplicationContext().getBean(QuestionDAO.class);
    AnswerDAO answerDAO = ApplicationContextProvider.getApplicationContext().getBean(AnswerDAO.class);
    ProposedAnswerDAO proposedAnswerDAO = ApplicationContextProvider.getApplicationContext().getBean(ProposedAnswerDAO.class);


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

    @RequestMapping(value = "correct-answer", method = RequestMethod.POST)
    public
    @ResponseBody
    String setCorrectAnswer(@RequestParam("testId") String testId,
                            @RequestParam("question") String question,
                            @RequestParam("answer") String answer) {

        JSONObject jo = new JSONObject();
        SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .filter(e -> e.getAuthority().equals(UsersRoles.ADMIN.getRole()))
                .findAny().map(ok -> {
            answerDAO.setCorrectAnswer(UUID.fromString(testId), question, answer);
            jo.put("status", 0).put("action", "set");
            return "";
        }).orElseGet(() -> {
            ProposedAnswer proposedAnswer = new ProposedAnswer();
            proposedAnswer.setQuestion(new Question(question, ""));
            proposedAnswer.setTest(new Test(UUID.fromString(testId)));
            proposedAnswer.setAnswer(new Answer(answer));
            jo.put("status", 0).put("action", "proposed");
            proposedAnswerDAO.addProposedAnswer(proposedAnswer);
            return "";
        });

        return jo.toString();
    }

    @RequestMapping(value = "proposed-answers")
    public ModelAndView getProposedAnswers() {
        return new ModelAndView("WEB-INF/pages/proposedAnswers");
    }

    @RequestMapping(value = "proposed-answers-json", produces = "application/json; charset=utf-8")
    public
    @ResponseBody
    String getProposedAnswersJson() {


        Map<String, Map<String, List<ProposedAnswer>>> proposedAnswerMap = proposedAnswerDAO.getAllProposedAnswers().stream()
                .collect(Collectors.groupingBy(e -> e.getTest().getName(),
                        Collectors.groupingBy(e -> e.getTest().getDiscipline()/*,
                                Collectors.groupingBy(e -> e.getTest().getGroup()))*/)));
        JSONArray jsonArray = new JSONArray();


        proposedAnswerMap.forEach((name, map) -> {
            JSONObject jo = new JSONObject();
            jo.put("testName", name);
            JSONArray subArray = new JSONArray();

            StringBuilder sb = new StringBuilder("");

            map.forEach((discipline, questions) -> {

                questions.forEach(e -> {
                    JSONObject joSub = new JSONObject();
                    joSub.put("answer", e.getAnswer().getContent());
                    joSub.put("question", e.getQuestion().getName());
                    joSub.put("testId", e.getTest().getTestId());
                    joSub.put("proposedAnswerId", e.getId());
                    subArray.put(joSub);
                });
                sb.append(discipline).append(", ");
            });

            jo.put("discipline", sb.toString().trim().length() > 0 ? sb.toString().trim().substring(0, sb.toString().trim().length() - 1) : "");
            jo.put("answers", subArray);
            jsonArray.put(jo);
        });
        System.out.println(jsonArray.toString());

        return jsonArray.toString();
    }

    @RequestMapping(value = "accept-proposed-answer", method = RequestMethod.POST)
    public
    @ResponseBody
    String acceptProposedAnswer(@RequestParam("testId") String testid,
                                @RequestParam("question") String question,
                                @RequestParam("answer") String answer,
                                @RequestParam("id") int id) {

        ProposedAnswer proposedAnswer = new ProposedAnswer();
        proposedAnswer.setTest(new Test(UUID.fromString(testid)));
        proposedAnswer.setQuestion(new Question(question, ""));
        proposedAnswer.setAnswer(new Answer(answer));
        proposedAnswer.setId(id);

        proposedAnswerDAO.acceptProposedAnswer(proposedAnswer);

        return "";
    }

    @RequestMapping(value = "reject-proposed-answer", method = RequestMethod.POST)
    public
    @ResponseBody
    String rejectProposedAnswer(@RequestParam("id") int id) {

        ProposedAnswer proposedAnswer = new ProposedAnswer();
        proposedAnswer.setId(id);

        proposedAnswerDAO.rejectProposedAnswer(proposedAnswer);

        return "";
    }
}
