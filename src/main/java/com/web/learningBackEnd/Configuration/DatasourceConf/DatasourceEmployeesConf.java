package com.web.learningBackEnd.Configuration.DatasourceConf;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.web.learningBackEnd.Repository.employees",
        entityManagerFactoryRef = "employeeDb",
        transactionManagerRef = "employeeTransactionManager"
)
@EnableConfigurationProperties(Datasource.class)
public class DatasourceEmployeesConf {
    @Value("spring.datasource.employee.url")
    private String url;
    @Autowired
    private Datasource source;
    @Primary
    @Bean(name = "employeeDb")
    public LocalContainerEntityManagerFactoryBean configureTestDatasource(@Qualifier("employeeDatasource") DataSource testDataSource){
        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setDataSource(testDataSource);
        entityManager.setPackagesToScan("com.web.learningBackEnd.Model.entity.db_test");
        entityManager.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManager.setPersistenceUnitName("employeeDatasource");
        return entityManager;
    }
    @Bean("employeeDatasource")
    public DataSource userDataSource() {
        DriverManagerDataSource em = new DriverManagerDataSource();
        em.setPassword(source.getPassword());
        em.setUsername(source.getUsername());
        em.setUrl(url);
        em.setDriverClassName(source.getDriver());
        return em;
    }
    @Bean("employeeTransactionManager")
    public PlatformTransactionManager userTransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                configureTestDatasource(userDataSource()).getObject());
        return transactionManager;
    }
    @Primary
    @Bean("employeeJDBCTemplate")
    public JdbcTemplate todosJdbcTemplate(@Qualifier("employeeDatasource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
