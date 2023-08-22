package com.web.learningBackEnd.Service;

import com.web.learningBackEnd.Mapper.EmployeeMapper;
import com.web.learningBackEnd.Model.entity.db_test.Employee;
import com.web.learningBackEnd.Model.request.DatePlage;
import com.web.learningBackEnd.Model.request.RequestedEmployee;
import com.web.learningBackEnd.Repository.employees.CustomRepositoryForFiltering;
import com.web.learningBackEnd.Repository.employees.EmployeeRepository;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;
    @Autowired
    public EmployeeService(EmployeeRepository repository, EmployeeMapper mapper, PhoneNumberService phoneNumberService, CustomRepositoryForFiltering customRepositoryForFiltering) {
        this.repository = repository;
        this.mapper = mapper;
        this.phoneNumberService = phoneNumberService;
        this.customRepositoryForFiltering = customRepositoryForFiltering;
    }

    private final PhoneNumberService phoneNumberService;
    private final CustomRepositoryForFiltering customRepositoryForFiltering;
    public List<RequestedEmployee> getAll(){
        return repository.findAll().stream().map(mapper::toRest).toList();
    }
    public RequestedEmployee getEmployeeByMatricule(String matricule){
        return mapper.toRest(repository.getEmployeeByMatricule(matricule));
    }
    public String saveEmployee(Employee employee){
        try{
            Employee tosave = repository.save(employee);
            phoneNumberService.UpadteAllEmployee(employee.getPhonenumber(), employee.getId());
            return "success";
        }
        catch (RuntimeException exception){
            throw exception;
        }
    }
    public List<RequestedEmployee> filterEmployee(
            @Nullable String CountryCode,
            @Nullable String lastName,
            @Nullable String firstName,
            @Nullable String birthday,
            @Nullable DatePlage start,
            @Nullable DatePlage end,
            @Nullable Employee.SEX sex
            ){
        return customRepositoryForFiltering.filterEmployee(CountryCode,lastName,firstName,birthday,start,end,sex).stream().map(mapper::toRest).toList();
    }
}
