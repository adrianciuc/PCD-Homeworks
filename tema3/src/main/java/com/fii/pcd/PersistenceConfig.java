package com.fii.pcd;

import com.google.appengine.api.utils.SystemProperty;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories("com.fii.pcd.repository")
public class PersistenceConfig {


    private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Production){
            dataSource.setDriverClassName("com.mysql.jdbc.GoogleDriver");
            dataSource.setUrl(System.getProperty("cloudsql.url"));
        } else {
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl(System.getProperty("cloudsql.url.dev"));
        }
        dataSource.setUsername("edue");
        dataSource.setPassword("1234");

        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
        entityManagerFactoryBean.setPackagesToScan("com.fii.pcd.model");

        entityManagerFactoryBean.setJpaProperties(hibProperties());

        return entityManagerFactoryBean;
    }

    private Properties hibProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
//        properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, "true");
//        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}
