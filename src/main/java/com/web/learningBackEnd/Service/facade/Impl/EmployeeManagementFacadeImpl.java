package com.web.learningBackEnd.Service.facade.Impl;

import com.lowagie.text.DocumentException;
import com.web.learningBackEnd.Controller.utils.InputFormat;
import com.web.learningBackEnd.Controller.utils.UserInformation;
import com.web.learningBackEnd.Mapper.EmployeeMapper;
import com.web.learningBackEnd.Mapper.UserInformationMapper;
import com.web.learningBackEnd.Model.entity.db_test.CompanyInformation;
import com.web.learningBackEnd.Model.entity.db_test.Employee;
import com.web.learningBackEnd.Model.entity.db_test.User;
import com.web.learningBackEnd.Model.request.RequestedCompanyInformation;
import com.web.learningBackEnd.Model.request.RequestedEmployee;
import com.web.learningBackEnd.Model.request.SaveEmployee;
import com.web.learningBackEnd.Model.request.UserLogin;
import com.web.learningBackEnd.Repository.employees.CompanyInformationRepository;
import com.web.learningBackEnd.Repository.employees.CountryCodeRepository;
import com.web.learningBackEnd.Service.EmployeeService;
import com.web.learningBackEnd.Service.PDFGeneratingService;
import com.web.learningBackEnd.Service.SecurityService;
import com.web.learningBackEnd.Service.facade.EmployeeManagementFacade;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Service
public class EmployeeManagementFacadeImpl implements EmployeeManagementFacade {
    private final EmployeeService service;
    private final SecurityService security;
    private final EmployeeMapper employeeMapper;
    private final CountryCodeRepository countryCodeRepository;
    private final CompanyInformationRepository companyInformationRepository;
    private final PDFGeneratingService pdfGeneratingService;
    private final UserInformationMapper userInformationMapper;
    @Override
    @Primary
    public List<RequestedEmployee> listAllEmployee(InputFormat by) {
        by.clean();
        if(by.IsEmpty()){
            return service.getAll();
        }else{
            return service.filterEmployee(by.getCountryCode(),by.getLastName(),by.getFirstName(),by.getBirthday(),by.getStart(),by.getEnd(),by.getSex());
        }
    }
    public User verifyUserSession(HttpSession session) throws IOException {
        return security.AuthentifyUser(session);
    }
    @Override
    @Primary
    public User SignUp(UserInformation input, HttpSession session) {
        User identity = security.authentifyUserOnLogin(input);
        if(identity!=null){
            security.setTokenSession(identity,session);
        }
        return identity;
    }

    @Override
    @Primary
    public User SignIn(UserLogin input) {
        return security.saveUser(userInformationMapper.toEntity(input));
    }

    @Override
    @Primary
    public String ManageEmployee(SaveEmployee input) throws IOException {
        return service.saveEmployee(employeeMapper.toEntity(input));
    }

    @Override
    @Primary
    public RequestedEmployee getEmployeeDetails(String matricule) {
        return service.getEmployeeByMatricule(matricule);
    }

    @Override
    @Primary
    public CountryCodeRepository getcountryCodeInstance() {
        return countryCodeRepository;
    }

    @Override
    public User authentifyUser(HttpSession input) {
        return security.AuthentifyUser(input);
    }

    @Override
    public void getPdf(String matricule, OutputStream outputStream) throws DocumentException, MalformedURLException {
        System.out.println("arrived int the facade");
        CompanyInformation companyInformation = companyInformationRepository.findAll().get(0);
        RequestedCompanyInformation insert = RequestedCompanyInformation.builder()
                .companyLogo(Base64.getEncoder().encodeToString(companyInformation.getCompanyLogo()))
                .companyName(companyInformation.getCompanyName())
                .nif(companyInformation.getNif())
                .number(companyInformation.getNumber())
                .stat(companyInformation.getStat())
                .address(companyInformation.getAddress())
                .email(companyInformation.getEmail())
                .companySlogan(companyInformation.getCompanySlogan())
                .build();
        RequestedEmployee employee = service.getEmployeeByMatricule(matricule);
        System.out.println("the employee is here : "+employee.getFirstName());
        String html = pdfGeneratingService.parseThymeleafTemplate(employee,insert);
        System.out.println(html);
        pdfGeneratingService.generatePdfFromHtml(html,outputStream);
    }
}
