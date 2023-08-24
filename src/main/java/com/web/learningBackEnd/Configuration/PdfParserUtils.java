package com.web.learningBackEnd.Configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class PdfParserUtils implements ApplicationContextAware {
    private static TemplateEngine templateEngine;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        templateEngine = applicationContext.getBean(TemplateEngine.class);
    }

    public  String processTemplate(String templateName, Context context) {
        return templateEngine.process(templateName, context);
    }
}