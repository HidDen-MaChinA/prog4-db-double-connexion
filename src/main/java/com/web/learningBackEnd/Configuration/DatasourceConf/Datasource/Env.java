package com.web.learningBackEnd.Configuration.DatasourceConf.Datasource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.datasource.cnaps")
@Getter
@Setter
public class Env {
    private String username;
    private String password;
    private String driver;
}
