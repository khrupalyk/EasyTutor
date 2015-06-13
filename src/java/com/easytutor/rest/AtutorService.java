package com.easytutor.rest;
import com.easytutor.rest.obj.QuestionInfo;
import com.sun.net.httpserver.HttpServer;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by root on 12.06.15.
 */
@Path("atutor")
public class AtutorService {

    @POST
    @Path("objects")
    @Produces(MediaType.APPLICATION_JSON)
    public void storeObjects(List<QuestionInfo> questionInfoList){
        Logger.getLogger(AtutorService.class.getName()).severe("Count element in list: " + questionInfoList.size());

        for (QuestionInfo questionInfo : questionInfoList) {
            Logger.getLogger(AtutorService.class.getName()).severe(questionInfo.toString());
        }
    }

    @OPTIONS
    @Path("objects")
    public Response getOptions() {
        return Response.ok()
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Accept, Content-type, X-Json, X-Prototype-Version, X-Requested-With")
                .build();
    }
}
