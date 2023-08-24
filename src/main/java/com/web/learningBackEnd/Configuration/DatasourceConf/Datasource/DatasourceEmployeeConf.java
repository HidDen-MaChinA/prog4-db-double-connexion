package com.web.learningBackEnd.Configuration.DatasourceConf.Datasource;


import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.web.learningBackEnd.Repository.employees",
        entityManagerFactoryRef = "EMFactoryEmployee",
        transactionManagerRef = "employeeTransactionManager"
)
public class DatasourceEmployeeConf {
    @Primary
    @Bean(name = "employeeDatasource")
    @ConfigurationProperties(prefix = "spring.datasource.employee")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }
    @Primary
    @Bean(name = "EMFactoryEmployee")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory1(
            EntityManagerFactoryBuilder builder,
            @Qualifier("employeeDatasource") DataSource dataSource
    ) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        return builder
                .dataSource(dataSource)
                .packages("com.web.learningBackEnd.Model.entity.db_test")
                .properties(properties)
                .build();
    }
    @Primary
    @Bean(name = "employeeTransactionManager")
    public PlatformTransactionManager transactionManager1(
            @Qualifier("EMFactoryEmployee") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}