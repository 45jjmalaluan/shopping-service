package com.ethoca.ss.web.config;

import org.flywaydb.core.Flyway;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Configuration class for a persistence setup.
 */
@Configuration
@EnableJpaRepositories({"com.ethoca.ss.core.repository"})
@EnableTransactionManagement
public class PersistenceConfig {
    private static final String ENTITY_PACKAGE = "com.ethoca.ss.core.entity";

    private static final String PROPERTY_DB_DRIVER = "db.driver";

    private static final String PROPERTY_DB_URL = "db.url";

    private static final String PROPERTY_DB_USERNAME = "db.username";

    private static final String PROPERTY_DB_PASSWORD = "db.password";

    private static final String PROPERTY_HIBERNATE_DIALECT = "hibernate.dialect";

    private static final String PROPERTY_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

    private static final String PROPERTY_FLYWAY_SQL_LOCATIONS = "flyway.sql-locations";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_DB_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROPERTY_DB_URL));
        dataSource.setUsername(env.getRequiredProperty(PROPERTY_DB_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROPERTY_DB_PASSWORD));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setPackagesToScan(ENTITY_PACKAGE);
        entityManagerFactory.setJpaProperties(hibernateProperties());
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    @Bean
    public Flyway flyway() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource());
        // Get locations
        String locationsStr = env.getProperty(PROPERTY_FLYWAY_SQL_LOCATIONS);
        flyway.setLocations(locationsStr);
        flyway.clean();
        // initializes schema_version if not existing
        flyway.setBaselineOnMigrate(true);
        flyway.migrate();
        return flyway;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(PROPERTY_HIBERNATE_DIALECT, env.getRequiredProperty(PROPERTY_HIBERNATE_DIALECT));
        properties.put(PROPERTY_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_HIBERNATE_SHOW_SQL));
        return properties;
    }
}
