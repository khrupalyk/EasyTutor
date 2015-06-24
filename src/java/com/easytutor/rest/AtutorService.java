package com.easytutor.rest;
import com.easytutor.rest.obj.QuestionInfo;
import com.easytutor.rest.obj.TestInfo;
import com.easytutor.rest.obj.TestScores;
import com.easytutor.utils.ApplicationContextProvider;
import com.easytutor.utils.TemporaryTestStorage;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.naming.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by root on 12.06.15.
 */
@Path("atutor")
public class AtutorService {

    private TemporaryTestStorage tempTestIds = (TemporaryTestStorage) ApplicationContextProvider.getBean("TemporaryTestStorage");

    @POST
    @Path("test/questions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response storeObjects(TestInfo testInfo){
        Logger.getLogger(AtutorService.class.getName()).info("Count element in list: " + testInfo.getBody().size());

        for (QuestionInfo questionInfo : testInfo.getBody()) {
            Logger.getLogger(AtutorService.class.getName()).severe(questionInfo.toString());
        }
        return Response.ok(UUID.randomUUID()).build();
    }

    @POST
    @Path("test/scores")
    @Produces(MediaType.APPLICATION_JSON)
    public void storeTestResult(TestScores testScores){
        Logger.getLogger(AtutorService.class.getName()).info("Test scores: " + testScores);
    }

    @GET
    @Path("test/temp-test/{discipline}/{group}")
    public String generateTempTestId(@PathParam("discipline") String discipline,
                                     @PathParam("group") String group) {
        UUID testId = UUID.randomUUID();
        tempTestIds.putTestId(testId);
        // TODO: If text exist return test id, else return temp id.
        return testId.toString();
    }

    @OPTIONS
    @Path("/")
    public Response getOptions() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Accept, Content-type, X-Json, X-Prototype-Version, X-Requested-With")
                .build();
    }
}
