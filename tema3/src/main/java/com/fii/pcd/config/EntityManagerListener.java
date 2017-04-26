package com.fii.pcd.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EntityManagerListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext()
                .setAttribute(
                        "emf",
                        Persistence.createEntityManagerFactory("edue-persistence-unit"));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        EntityManagerFactory factory = (EntityManagerFactory)sce.getServletContext().getAttribute("emf");
        factory.close();
    }
}
