package com.easytutor.api.rest;

import com.easytutor.dao.TestDAO;
import com.easytutor.models.Test;
import com.easytutor.utils.ApplicationContextProvider;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by root on 04.07.15.
 */

@Path("test")
public class TestsService {

    private TestDAO testDAO = ApplicationContextProvider.getApplicationContext().getBean(TestDAO.class);

    @GET
    @Path("tests/unique")
    @Produces(MediaType.APPLICATION_JSON)
    public String generateUniqueTests() {

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

        return ja.toString();

    }
}
