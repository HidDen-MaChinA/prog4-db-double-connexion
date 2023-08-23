package com.web.learningBackEnd.Repository.Cnaps;

import com.web.learningBackEnd.Model.entity.db_cnaps.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("cnapsEmployee")
@Qualifier("EMFactoryCnaps")
public interface EmployeeRepository extends JpaRepository<Employee,String> {
    Employee getEmployeeByCnaps(String cnaps);
}
