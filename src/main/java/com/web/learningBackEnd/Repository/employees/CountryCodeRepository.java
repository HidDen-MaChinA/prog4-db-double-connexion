package com.web.learningBackEnd.Repository.employees;

import com.web.learningBackEnd.Model.entity.db_test.CountryCode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Qualifier("EMFactoryEmployee")
public interface CountryCodeRepository extends JpaRepository<CountryCode,String> {
    @Modifying
    @Query(nativeQuery = true, value = "update country_code set phone_number_id=:phoneNumber where password=:password")
    @Transactional
    void setPhoneNumber(String phoneNumber);
}
