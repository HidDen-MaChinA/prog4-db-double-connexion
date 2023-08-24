package com.web.learningBackEnd.Configuration.DatasourceConf;

import com.web.learningBackEnd.Configuration.DatasourceConf.Datasource.DatabaseContext;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DatabaseSwitchAspect {

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void switchDatabase(JoinPoint joinPoint) {
        if (joinPoint.getTarget() instanceof MyService) {
            if (/* some condition to determine database */) {
                DatabaseContext.setDatabaseKey("PRIMARY");
            } else {
                DatabaseContext.setDatabaseKey("SECONDARY");
            }
        }
    }

    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void clearDatabase() {
        DatabaseContext.clearDatabaseKey();
    }
}
