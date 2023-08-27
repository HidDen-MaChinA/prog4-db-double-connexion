package com.web.learningBackEnd.Repository.employees;

import com.web.learningBackEnd.Model.entity.db_test.CompanyInformation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier("EMFactoryEmployee")
public interface CompanyInformationRepository extends JpaRepository<CompanyInformation,String> {
}
