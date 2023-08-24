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
@AllArgsConstructor
public class EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

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
            @Nullable Integer sex
            ){
        return customRepositoryForFiltering.filterEmployee(CountryCode,lastName,firstName,birthday,start,end,sex).stream().map(mapper::toRest).toList();
    }
}
