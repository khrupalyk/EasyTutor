package com.easytutor.orm;

import com.easytutor.dao.impl.QuestionDAOImpl;
import com.easytutor.dao.impl.TestDAOImpl;
import com.easytutor.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

/**
 * Created by root on 04.07.15.
 */
public class AggregationTest {

    Session session = HibernateUtil.getSessionFactory().openSession();

    @Test
    public void aggregationTestTest() {
        UUID testId = UUID.fromString("2f122484-cc03-4bb6-936c-5a700830ca03");
        TestDAOImpl testDAO = new TestDAOImpl();
        QuestionDAOImpl questionDAO = new QuestionDAOImpl();
        questionDAO.setSessionFactory(HibernateUtil.getSessionFactory());
        questionDAO.setTestDAO(testDAO);
        testDAO.setSessionFactory(HibernateUtil.getSessionFactory());
        testDAO.setQuestionDAO(questionDAO);

        long start = 0;
        long end = 0;
        com.easytutor.models.Test test = null;
        for (int i = 0; i < 100; i++){
            start = System.currentTimeMillis();
            test = testDAO.getTestWithQuestionStatistic(testId);
             end = System.currentTimeMillis();
            System.out.println("#" + i + " time: " + (end - start));
        }
        System.out.println("\n\n" + test + " " + start + end);


//        List results = session.createCriteria(com.easytutor.models.Test.class)
//                .setProjection(Projections.projectionList()
//                                .add(Projections.groupProperty("course"), "course")
//                                .add(Projections.groupProperty("group"), "group")
//                                .add(Projections.groupProperty("name"), "name")
//                                .add(Projections.groupProperty("discipline"), "discipline")
//                                .add(Projections.sqlProjection(
//                                        "count(*) as count",
//                                        new String[]{"count"},
//                                        new Type[]{StandardBasicTypes.INTEGER}
//                                ), "testCount")
//                ).setResultTransformer(Transformers.aliasToBean(com.easytutor.models.Test.class))
//                .list();
//        System.out.println("Count results: " + results.get(0));
        session.close();

    }
}
