package com.web.learningBackEnd.Service;

import com.web.learningBackEnd.Mapper.PhoneNumberMapper;
import com.web.learningBackEnd.Model.entity.db_test.PhoneNumber;
import com.web.learningBackEnd.Repository.employees.CountryCodeRepository;
import com.web.learningBackEnd.Repository.employees.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
@AllArgsConstructor
public class PhoneNumberService {
    private final PhoneNumberRepository repository;
    private final PhoneNumberMapper phoneNumberMapper;
    private final CountryCodeRepository countryCodeRepository;

    public void UpadteAllEmployee(List<PhoneNumber> phoneNumberList, String EmployeeId){
        phoneNumberList.forEach(phoneNumber -> {
                    repository.setEmployee(EmployeeId, phoneNumber.getId());
                }
        );
    }
}
