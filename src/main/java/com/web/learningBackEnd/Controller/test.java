package com.web.learningBackEnd.Controller;

import com.lowagie.text.DocumentException;
import com.web.learningBackEnd.Model.entity.db_test.CountryCode;
import com.web.learningBackEnd.Model.entity.db_test.Employee;
import com.web.learningBackEnd.Model.request.DatePlage;
import com.web.learningBackEnd.Model.request.RequestedEmployee;
import com.web.learningBackEnd.Repository.employees.CountryCodeRepository;
import com.web.learningBackEnd.Repository.employees.CustomRepositoryForFiltering;
import com.web.learningBackEnd.Service.EmployeeService;
import com.web.learningBackEnd.Service.facade.EmployeeManagementFacade;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class test {
    private final EmployeeService service;
    private final CountryCodeRepository repository;
    private final CustomRepositoryForFiltering custom;
    private final EmployeeManagementFacade facade;
    @GetMapping("/test")
    public List<RequestedEmployee> get(){
        return service.getAll();
    }
    @GetMapping("/phone")
    public List<CountryCode> getl(){
        return repository.findAll();
    }
    @GetMapping("/test/filtered")
    public List<Employee> anotherTest(
            @Nullable @RequestParam("lastname") String lastName,
            @Nullable @RequestParam("firstname") String firstName,
            @Nullable @RequestParam("birthday") String birthday,
            @Nullable @RequestParam("start") DatePlage start,
            @Nullable @RequestParam("end") DatePlage end,
            @Nullable @RequestParam("sex") Integer sex
    ){
        return custom.filterEmployee("asd",lastName,firstName,birthday,start,end,sex);
    }
    @GetMapping("/pdf/{employee}")
    public void getPdf(@PathVariable String employee, HttpServletResponse response) throws DocumentException,IOException {
     facade.getPdf(employee,response.getOutputStream());
    }
}
