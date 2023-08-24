package com.web.learningBackEnd.Configuration.DatasourceConf;

import com.web.learningBackEnd.Configuration.DatasourceConf.Datasource.DatabaseContext;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContext.getDatabaseKey();
    }
}
