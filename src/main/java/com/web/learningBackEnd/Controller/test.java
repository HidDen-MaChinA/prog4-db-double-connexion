package com.web.learningBackEnd.Controller;

import com.web.learningBackEnd.Model.entity.db_test.CountryCode;
import com.web.learningBackEnd.Model.entity.db_test.Employee;
import com.web.learningBackEnd.Model.request.DatePlage;
import com.web.learningBackEnd.Model.request.RequestedEmployee;
import com.web.learningBackEnd.Repository.employees.CountryCodeRepository;
import com.web.learningBackEnd.Repository.employees.CustomRepositoryForFiltering;
import com.web.learningBackEnd.Service.EmployeeService;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class test {
    private final EmployeeService service;
    private final CountryCodeRepository repository;
    private final CustomRepositoryForFiltering custom;
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
            @Nullable @RequestParam("sex") Employee.SEX sex
    ){
        return custom.filterEmployee("asd",lastName,firstName,birthday,start,end,sex);
    }
}
