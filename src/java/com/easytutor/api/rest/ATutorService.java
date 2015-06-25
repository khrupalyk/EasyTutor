package com.easytutor.api.rest;
import com.easytutor.api.rest.obj.QuestionInfo;
import com.easytutor.api.rest.obj.TestInfo;
import com.easytutor.api.rest.obj.TestScores;
import com.easytutor.utils.ApplicationContextProvider;
import com.easytutor.utils.TemporaryTestStorage;

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

    private TemporaryTestStorage tempTestIds = (TemporaryTestStorage) ApplicationContextProvider.getBean("TemporaryTestStorage");

    @POST
    @Path("test/questions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeObjects(TestInfo testInfo){
        Logger.getLogger(ATutorService.class.getName()).info("Count element in list: " + testInfo.getBody().size());

        for (QuestionInfo questionInfo : testInfo.getBody()) {
            Logger.getLogger(ATutorService.class.getName()).severe(questionInfo.toString());
        }
        return Response.ok(UUID.randomUUID()).build();
    }

    @POST
    @Path("test/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public void storeTestResult(TestScores testScores){
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
