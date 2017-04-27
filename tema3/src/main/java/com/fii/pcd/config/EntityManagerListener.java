package com.fii.pcd.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.google.appengine.api.utils.SystemProperty;

import java.util.HashMap;
import java.util.Map;

public class EntityManagerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        Map<String, String> properties = new HashMap();

        if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.GoogleDriver");
            properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url"));
        }
        else{
            properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
            properties.put("javax.persistence.jdbc.url", System.getProperty("cloudsql.url.dev"));
        }

        sce.getServletContext()
                .setAttribute(
                        "emf",
                        Persistence.createEntityManagerFactory("edue-persistence-unit", properties));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory factory = (EntityManagerFactory)sce.getServletContext().getAttribute("emf");
        factory.close();
    }
}
