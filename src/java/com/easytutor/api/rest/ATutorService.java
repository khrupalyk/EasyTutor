package com.easytutor.api.rest;

import com.easytutor.api.rest.obj.*;
import com.easytutor.dao.*;
import com.easytutor.models.*;
import com.easytutor.utils.ApplicationContextProvider;
import com.easytutor.utils.TemporaryTestStorage;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by root on 12.06.15.
 */
@Path("/atutor")
public class ATutorService {

    private TemporaryTestStorage tempTestIds = (TemporaryTestStorage) ApplicationContextProvider.getApplicationContext().getBean("temporaryTestStorage");

    private TestDAO testDAO = ApplicationContextProvider.getApplicationContext().getBean(TestDAO.class);
    private QuestionDAO questionDAO = ApplicationContextProvider.getApplicationContext().getBean(QuestionDAO.class);
    private AnswerDAO answerDAO = ApplicationContextProvider.getApplicationContext().getBean(AnswerDAO.class);
    private UserATutorDAO userATutorDAO = ApplicationContextProvider.getApplicationContext().getBean(UserATutorDAO.class);
    private TestResultDAO testResultDAO = ApplicationContextProvider.getApplicationContext().getBean(TestResultDAO.class);

    @POST
    @Path("test/questions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeObjects(TestInfo testInfo) {

        Runnable task2 = () -> {

            UUID testId = UUID.fromString(testInfo.getTestId());
            Test test = new Test();
            test.setSubmissionTime(new Date());
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

            for (QuestionInfo question : questions.stream().distinct().collect(Collectors.toList())) {
                Question questionObj = new Question(question.getQuestion(), question.getQuestionHeader());
                Answer selectedAnswer = new Answer(question.getAnswer());
                List<String> answers = question.getAnswers();

                questionDAO.saveOrUpdate(questionObj);

                List<QuestionsAnswers> answersList = new ArrayList<>();
                for (String answer : answers.stream().distinct().collect(Collectors.toList())) {
                    Answer answerObj = new Answer(answer);
                    answerDAO.saveOrUpdate(answerObj);
                    answersList.add(createQuestionsAnswers(questionObj, answerObj, testId));
                }

                questionObj.setQuestionsAnswers(answersList);
                questionDAO.saveOrUpdate(questionObj);

                TestsQuestions testsQuestions1 = createTestQuestions(test, questionObj, selectedAnswer);
                testsQuestions.add(testsQuestions1);
                questionObj.getTestsQuestions().add(testsQuestions1);

            }
            test.setUserATutor(userATutor);
            test.setTestsQuestions(testsQuestions);

            testDAO.saveOrUpdate(test);

        };

        new Thread(task2).start();


        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }



    public QuestionsAnswers createQuestionsAnswers(Question question, Answer answer, UUID testId) {
        QuestionsAnswers questionsAnswers = new QuestionsAnswers();
        questionsAnswers.setQuestion(question);
        questionsAnswers.setTestId(testId);
        questionsAnswers.setAnswer(answer);
        return questionsAnswers;
    }

    public TestsQuestions createTestQuestions(Test test, Question question, Answer answer) {
        TestsQuestions testsQuestions = new TestsQuestions();
        testsQuestions.setTest(test);
        testsQuestions.setQuestion(question);
        testsQuestions.setSelectedAnswer(answer);
        return testsQuestions;
    }

    @POST
    @Path("test/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public void storeTestResult(TestScores testScores) {
        TestResult testResult = new TestResult();

        testResult.setTestId(UUID.fromString(testScores.getId()));
        testResult.setMax(testScores.getMaxScores());
        testResult.setCurrent(testScores.getScores());
        testResultDAO.storeTestResult(testResult);
        Logger.getLogger(ATutorService.class.getName()).info("Test scores: " + testScores);
    }

    @GET
    @Path("test/temp-test")
    public Response generateTempTestId() {
        UUID testId = UUID.randomUUID();
        System.out.println("Generated test id: " + testId);
//        tempTestIds.putTestId(testId);
        return Response.ok(testId.toString()).build();
    }

    @POST
    @Path("answer-for-question")
    @Produces(MediaType.APPLICATION_JSON)
    public FoundAnswer getAnswerForQuestion(LookingAnswer question) {
//    curl -XPOST http://localhost:8080/easytutor/rest/atutor/answer-for-question -d '{"testName" : "Модуль 1", "questionName":"Beб-caйт – цe", "discipline": "Програмування інтернет", "group": "СП-31"}' -H "Content-Type:application/json"


        return answerDAO.getAnswerByInfo(
                question.getTestName(),
                question.getDiscipline(),
                question.getQuestion(),
                extractCourseOpt(question.getGroup()),
                extractGroupOpt(question.getGroup()));
    }


//    @OPTIONS
//    @Path("/*")
//    public Response getOptions() {
//        return Response.ok()
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Headers", "Accept, Content-type, X-Json, X-Prototype-Version, X-Requested-With")
//                .build();
//    }

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

    public Optional<Integer> extractCourseOpt(String str) {
        try {
            String strNumber = str.split("-")[1];
            return Optional.of(Integer.valueOf(strNumber.charAt(0) + ""));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> extractGroupOpt(String string) {
        try {
            if (string.split("-")[0].trim().isEmpty())
                return Optional.empty();
            else
                return Optional.of(string.split("-")[0]);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
