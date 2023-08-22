package com.web.learningBackEnd.Repository.employees;

import com.web.learningBackEnd.Model.entity.db_test.PhoneNumber;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Qualifier("EMFactoryEmployee")
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber,String> {
    List<PhoneNumber> getPhoneNumbersByValue(String value);
    @Modifying
    @Transactional
    @Query(value = "update phone_number set user_id = :id where id = :phoneNumberId",nativeQuery = true)
    void setEmployee(String id,String phoneNumberId);
}
