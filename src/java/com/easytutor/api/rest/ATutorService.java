package com.easytutor.api.rest;

import com.easytutor.api.rest.obj.QuestionInfo;
import com.easytutor.api.rest.obj.TestInfo;
import com.easytutor.api.rest.obj.TestScores;
import com.easytutor.dao.AnswerDAO;
import com.easytutor.dao.QuestionDAO;
import com.easytutor.dao.TestDAO;
import com.easytutor.dao.UserATutorDAO;
import com.easytutor.dao.impl.TestDAOImpl;
import com.easytutor.models.Question;
import com.easytutor.models.Test;
import com.easytutor.utils.ApplicationContextProvider;
import com.easytutor.utils.TemporaryTestStorage;

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
        Logger.getLogger(ATutorService.class.getName()).info("Count element in list: " + testInfo.getBody().size());

        Test test = new Test();
        test.setName(testInfo.getModuleName());
        test.setTestId(UUID.fromString(testInfo.getTestId()));

        List<QuestionInfo> questions = testInfo.getBody();

        Question question = new Question();

//        for (QuestionInfo question : questions) {
//            question.
//        }

//        testDAO.saveOrUpdate(test);

        for (QuestionInfo questionInfo : testInfo.getBody()) {
            Logger.getLogger(ATutorService.class.getName()).severe(questionInfo.toString());
        }
        return Response.ok(UUID.randomUUID()).build();
    }

    @POST
    @Path("test/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public void storeTestResult(TestScores testScores) {
        Logger.getLogger(ATutorService.class.getName()).info("Test scores: " + testScores);
    }

    @GET
    @Path("test/temp-test")
    public String generateTempTestId() {
        UUID testId = UUID.randomUUID();
        tempTestIds.putTestId(testId);
        return testId.toString();
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
