package com.easytutor.api.rest;

import com.easytutor.api.rest.obj.QuestionInfo;
import com.easytutor.api.rest.obj.TestInfo;
import com.easytutor.api.rest.obj.TestScores;
import com.easytutor.dao.*;
import com.easytutor.dao.impl.TestDAOImpl;
import com.easytutor.models.*;
import com.easytutor.utils.ApplicationContextProvider;
import com.easytutor.utils.TemporaryTestStorage;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by root on 12.06.15.
 */
@Path("/")
public class ATutorService {

    private TemporaryTestStorage tempTestIds = (TemporaryTestStorage) ApplicationContextProvider.getApplicationContext().getBean("temporaryTestStorage");

    private TestDAO testDAO = ApplicationContextProvider.getApplicationContext().getBean(TestDAO.class);
    private QuestionDAO questionDAO = ApplicationContextProvider.getApplicationContext().getBean(QuestionDAO.class);
    private AnswerDAO answerDAO = ApplicationContextProvider.getApplicationContext().getBean(AnswerDAO.class);
    private UserATutorDAO userATutorDAO = ApplicationContextProvider.getApplicationContext().getBean(UserATutorDAO.class);

    @POST
    @Path("test/questions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeObjects(TestInfo testInfo) {

//        Logger

//        for (QuestionInfo questionInfo : testInfo.getBody()) {
//            try {
//                Logger.getLogger(ATutorService.class.getName()).severe("Test id: " + testInfo.getTestId());
//
//        aTutorDAO.saveTest(testInfo);
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//        }
//
//        Logger.getLogger(ATutorService.class.getName()).info("Count element in list: " + testInfo.getBody().size());

        UUID testId = UUID.fromString(testInfo.getTestId());
        Test test = new Test();
        test.setName(testInfo.getModuleName());
        test.setTestId(testId);
        test.setDiscipline(testInfo.getDiscipline());
        test.setGroup(extractGroup(testInfo.getGroup()));
        test.setCourse(getCourse(testInfo.getGroup()));

        List<QuestionInfo> questions = testInfo.getBody();

        UserATutor userATutor = new UserATutor();
        userATutor.setName(testInfo.getUser());

        userATutorDAO.saveOrUpdate(userATutor);

        List<TestsQuestions> testsQuestions = new ArrayList<>();

        for (QuestionInfo question : questions) {
            Question questionObj = new Question(question.getQuestion(), question.getQuestionHeader());
            Answer selectedAnswer = new Answer(question.getAnswer());
            List<String> answers = question.getAnswers();

            questionDAO.saveOrUpdate(questionObj);

            List<QuestionsAnswers> answersList = new ArrayList<>();
            for (String answer : answers) {
                Answer answerObj = new Answer(answer);
//                answerObj.getQuestions().add(questionObj);
                answerDAO.saveOrUpdate(answerObj);
                answersList.add(createQuestionsAnswers(questionObj, answerObj, testId));
            }

            questionObj.setQuestionsAnswers(answersList);
//            questionObj.setQuestionsAnswers(answersList);
            questionDAO.saveOrUpdate(questionObj);

            TestsQuestions testsQuestions1 = createTestQuestions(test, questionObj, userATutor, selectedAnswer);
            testsQuestions.add(testsQuestions1);
            questionObj.getTestsQuestions().add(testsQuestions1);

        }

        test.setTestsQuestions(testsQuestions);

        testDAO.saveOrUpdate(test);


        return Response.ok().build();
    }

    public int getCourse(String str) {
        try {
            String strNumber = str.split("-")[1];
            return Integer.valueOf(strNumber.charAt(0) + "");
        } catch (Exception e) {
            return 0;
        }
    }

    public String extractGroup(String string) {
        try {
            return string.split("-")[0];
        } catch (Exception e) {
            return "";
        }
    }

    public QuestionsAnswers createQuestionsAnswers(Question question, Answer answer, UUID testId) {
        QuestionsAnswers questionsAnswers = new QuestionsAnswers();
        questionsAnswers.setQuestion(question);
        questionsAnswers.setTestId(testId);
        questionsAnswers.setAnswer(answer);
        return questionsAnswers;
    }

    public TestsQuestions createTestQuestions(Test test, Question question, UserATutor userAtutor, Answer answer) {
        TestsQuestions testsQuestions = new TestsQuestions();
        testsQuestions.setTest(test);
        testsQuestions.setQuestion(question);
        testsQuestions.setUserATutor(userAtutor);
        testsQuestions.setSelectedAnswer(answer);
        return testsQuestions;
    }

    @POST
    @Path("test/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public void storeTestResult(TestScores testScores) {
        Logger.getLogger(ATutorService.class.getName()).info("Test scores: " + testScores);
    }

    @GET
    @Path("test/temp-test")
    public Response generateTempTestId() {
        UUID testId = UUID.randomUUID();
        tempTestIds.putTestId(testId);
        Logger.getLogger(ATutorService.class.getName()).info("Generated id: " + testId);
        return Response.ok(testId.toString()).build();
    }

    @OPTIONS
    @Path("/*")
    public Response getOptions() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Accept, Content-type, X-Json, X-Prototype-Version, X-Requested-With")
                .build();
    }
}
