package com.japarejo.springmvc.configuration.data;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@PropertySource({ "classpath:security-db.properties" })
@EnableJpaRepositories(
    basePackages = "com.japarejo.springmvc.security.repositories", 
    entityManagerFactoryRef = "securityEntityManager", 
    transactionManagerRef = "securityTransactionManager"
    )
public class SecurityDataSourceConfiguration {
	@Autowired
    private Environment env;
 
    @Bean
    public LocalContainerEntityManagerFactoryBean securityEntityManager() {
        LocalContainerEntityManagerFactoryBean em
          = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(securityDataSource());
        em.setPackagesToScan(
          new String[] { "com.japarejo.springmvc.security.entities" });
 
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto",
          "update");
        properties.put("hibernate.dialect",
          env.getProperty("security.hibernate.dialect"));
        em.setJpaPropertyMap(properties);
 
        return em;
    }
 
    @Bean
    public DataSource securityDataSource() {
  
        DriverManagerDataSource dataSource
          = new DriverManagerDataSource();
        dataSource.setDriverClassName(
          env.getProperty("security.jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("security.jdbc.url"));
        dataSource.setUsername(env.getProperty("security.jdbc.user"));
        dataSource.setPassword(env.getProperty("security.jdbc.pass"));
 
        return dataSource;
    }
 
    @Bean
    public PlatformTransactionManager securityTransactionManager() {
  
        JpaTransactionManager transactionManager
          = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
          securityEntityManager().getObject());
        return transactionManager;
    }
}
