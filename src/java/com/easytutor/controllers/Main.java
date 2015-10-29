package com.easytutor.controllers;

import com.easytutor.api.rest.ATutorService;
import com.easytutor.api.rest.obj.TestInfo;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by root on 18.10.15.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        TestInfo test = mapper.readValue(new File("/home/saigak/Завантаження/result (1).json"), TestInfo.class);
        ATutorService aTutorService = new ATutorService();
        aTutorService.storeObjects(test);
        System.out.println(test.getDiscipline());
        System.out.println(test.getTestId());
        Thread.sleep(20000);
    }
}
