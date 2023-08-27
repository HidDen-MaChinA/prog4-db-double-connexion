package com.web.learningBackEnd.Service;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.codec.Base64;
import com.web.learningBackEnd.Configuration.PdfParserUtils;
import com.web.learningBackEnd.Model.entity.db_test.CompanyInformation;
import com.web.learningBackEnd.Model.entity.db_test.User;
import com.web.learningBackEnd.Model.request.RequestedCompanyInformation;
import com.web.learningBackEnd.Model.request.RequestedEmployee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Service
@AllArgsConstructor
@Getter
@Setter
public class PDFGeneratingService {
    private final PdfParserUtils utils;
    public String parseThymeleafTemplate(RequestedEmployee user, RequestedCompanyInformation company) {
        System.out.println("arrived in the : parser");
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        System.out.println("after the Template engine was set");
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("company", company);
        System.out.println("setting the context for the value");
        return utils.processTemplate("toPdf",context);
    }
    public void generatePdfFromHtml(String html, OutputStream stream) throws DocumentException {
        System.out.println("arriver in the pdf generator");
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(stream);
    }
}
