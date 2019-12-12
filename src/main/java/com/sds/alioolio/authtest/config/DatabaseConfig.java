package com.sds.alioolio.authtest.config;

import javax.sql.DataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Date: 2019-05-31
 * Developer: dongha.shin
 * Description : Database Configuration
 */
@Configuration
@EnableConfigurationProperties(LiquibaseProperties.class)
public class DatabaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource, LiquibaseProperties liquibaseProperties) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:liquibase.xml");
        liquibase.setContexts(liquibaseProperties.getContexts());
        liquibase.setDefaultSchema(liquibaseProperties.getDefaultSchema());
        liquibase.setDropFirst(liquibaseProperties.isDropFirst());
        liquibase.setShouldRun(liquibaseProperties.isEnabled());

        return liquibase;
    }
}
