package com.easytutor.orm;

import com.easytutor.utils.HibernateUtil;
import org.junit.Test;

/**
 * Created by root on 22.06.15.
 */
public class TestsMappingTest {

    @Test
    public void simple(){

        HibernateUtil.getSessionFactory();

    }
}
