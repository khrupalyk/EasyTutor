package com.easytutor.orm;

import com.easytutor.models.Answer;
import com.easytutor.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by root on 27.06.15.
 */
public class EncodingTest {

    Session session = HibernateUtil.getSessionFactory().openSession();

    @Before
    public void beforeAll() throws IOException {

        String text = new String(Files.readAllBytes(Paths.get("db/scheme.sql")), StandardCharsets.UTF_8);
        String queries[] = text.split(";");
        for (String query : queries) {
            if (!query.trim().isEmpty())
                session.createSQLQuery(query.replace("\n", "")).executeUpdate();
        }
    }

    @Test
    public void encodingTest() throws UnsupportedEncodingException {
        String str = new String("Стрічка".getBytes(), "UTF-8");
        Answer answer = new Answer(str);
        session.beginTransaction();


//        session.
        session.save(answer);

        session.getTransaction().commit();

        session.close();
    }
}
