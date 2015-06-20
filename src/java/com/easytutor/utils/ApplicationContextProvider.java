package com.easytutor.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created by root on 20.06.15.
 */
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;

    public ApplicationContext getApplicationContext() {
        return context;
    }

    public void setApplicationContext(ApplicationContext ctx) {
        context = ctx;
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }
}