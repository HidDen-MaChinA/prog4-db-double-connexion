package com.web.learningBackEnd.Configuration.DatasourceConf.Datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContext.getDatabaseKey();
    }
}
