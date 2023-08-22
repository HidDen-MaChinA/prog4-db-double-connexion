package com.web.learningBackEnd.Repository.employees;

import com.web.learningBackEnd.Model.entity.db_test.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("EMFactoryEmployee")
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    Employee getEmployeeByMatricule(String matricule);
}
