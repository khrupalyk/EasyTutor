package com.easytutor.orm;

import com.easytutor.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;
import org.junit.Test;

import java.util.List;

/**
 * Created by root on 04.07.15.
 */
public class AggregationTest {

    Session session = HibernateUtil.getSessionFactory().openSession();

    @Test
    public void aggregationTestTest() {

        List results = session.createCriteria(com.easytutor.models.Test.class)
                .setProjection(Projections.projectionList()
                                .add(Projections.groupProperty("course"), "course")
                                .add(Projections.groupProperty("group"), "group")
                                .add(Projections.groupProperty("name"), "name")
                                .add(Projections.groupProperty("discipline"), "discipline")
                                .add(Projections.sqlProjection(
                                        "count(*) as count",
                                        new String[]{"count"},
                                        new Type[]{StandardBasicTypes.INTEGER}
                                ), "testCount")
                ).setResultTransformer(Transformers.aliasToBean(com.easytutor.models.Test.class))
                .list();
        System.out.println("Count results: " + results.get(0));
        session.close();

    }
}
