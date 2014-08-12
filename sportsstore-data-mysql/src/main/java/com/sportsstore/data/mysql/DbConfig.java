package com.sportsstore.data.mysql;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
//@PropertySource({"classpath:com.sportsstore.data.mysql"})
public class DbConfig {
    @Bean(destroyMethod = "close")
    public BasicDataSource basicDataSource(){
        BasicDataSource src = new BasicDataSource();
        src.setDriverClassName("com.mysql.jdbc.Driver");
        src.setUrl("jdbc:mysql://localhost:3306/sportsstore");
        src.setUsername("dbuser");
        src.setPassword("password");
        return src;
    }

    @Bean
    @Primary
    public NamedParameterJdbcTemplate jdbcTemplate(){
        return new NamedParameterJdbcTemplate(basicDataSource());
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(){
        return new DataSourceTransactionManager(basicDataSource());
    }

    @Bean
    public TransactionTemplate transactionTemplate(){
        return new TransactionTemplate(dataSourceTransactionManager());
    }

    // Adds an advisor to any bean that's annotated with @Repository so that any platform-specific
    // exceptions are caught and then rethrown as one of Spring's unchecked data access exceptions.
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
