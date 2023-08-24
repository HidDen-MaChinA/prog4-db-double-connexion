package com.web.learningBackEnd.Configuration.DatasourceConf.Datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class RoutingDataSourceConf {
    @Bean
    @Primary
    public DataSource dataSource(@Qualifier("EMFactoryEmployee") DataSource primaryDataSource,
                                 @Qualifier("EMFactoryCnaps") DataSource secondaryDataSource) {
        RoutingDataSource routingDataSource = new RoutingDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("PRIMARY", primaryDataSource);
        targetDataSources.put("SECONDARY", secondaryDataSource);
        routingDataSource.setTargetDataSources(targetDataSources);
        routingDataSource.setDefaultTargetDataSource(primaryDataSource);
        return routingDataSource;
    }
}
