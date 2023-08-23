package com.web.learningBackEnd.Repository.employees;

import com.web.learningBackEnd.Model.entity.db_test.Employee;
import com.web.learningBackEnd.Model.request.DatePlage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import java.util.List;

@Qualifier("EMFactoryEmployee")
public interface CustomRepositoryForFiltering {
    List<Employee> filterEmployee(String country_code, String lastName, String firstName, String birthday, DatePlage start, DatePlage leave, Integer sex);
}
