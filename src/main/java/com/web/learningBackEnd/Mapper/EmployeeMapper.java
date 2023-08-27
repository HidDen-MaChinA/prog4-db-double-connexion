package com.web.learningBackEnd.Mapper;

import com.web.learningBackEnd.Mapper.utils.ConvertDate;
import com.web.learningBackEnd.Mapper.utils.PhoneNumberUtils;
import com.web.learningBackEnd.Mapper.utils.RandomString;
import com.web.learningBackEnd.Model.entity.db_test.Employee;
import com.web.learningBackEnd.Model.request.RequestedEmployee;
import com.web.learningBackEnd.Model.request.SaveEmployee;
import com.web.learningBackEnd.Model.request.SavePhoneNumber;
import com.web.learningBackEnd.Repository.Cnaps.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Component
@AllArgsConstructor
@Getter
@Setter
public class EmployeeMapper {
    private final PhoneNumberMapper phoneNumberMapper;
    private final PhoneNumberUtils utils;
    private final EmployeeRepository employeeRepository;
    private final RandomString randomString;
    private final ConvertDate convertDate;
    public Employee toEntity(SaveEmployee input) throws IOException {
        List<SavePhoneNumber> phoneNumberToSave = utils.TransformStringPhoneNumber(input.getPhoneNumber()).stream().map(value ->phoneNumberMapper.toSavePhoneNumber(value,input.getCountryCode())).toList();
        com.web.learningBackEnd.Model.entity.db_cnaps.Employee cnaps = employeeRepository.getEmployeeByCnaps(input.getCnaps());
        System.out.println(cnaps);
        return Employee.builder()
                .id(UUID.randomUUID().toString())
                .address(input.getAddress())
                .brutSalary(input.getBrutSalary())
                .birthDate(convertDate.getSqlDateFormat(input.getBirthDate()))
                .cinCreationDate(input.getCinCreationDate())
                .cinNumber(input.getCinNumber())
                .cnaps(avoidNull(cnaps))
                .emailPerso(input.getEmailPerso())
                .emailPro(input.getEmailPro())
                .dateEntre(convertDate.getSqlDateFormat(input.getDate_entre()))
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .phonenumber(phoneNumberToSave.stream().map(savePhoneNumber -> phoneNumberMapper.toEntity(savePhoneNumber,input.getCountryCode())).toList())
                .sex(input.getSex())
                .matricule("Employee"+randomString.getAlphaNumericString(6))
                .photo(input.getPhoto().getBytes())
                .build();
    }
    private String avoidNull(com.web.learningBackEnd.Model.entity.db_cnaps.Employee value){
        try{
            return value.getCnaps();
        }catch (RuntimeException e){
            return null;
        }
    }
    public RequestedEmployee toRest(Employee input) {
        return RequestedEmployee.builder()
                .address(input.getAddress())
                .birthDate(input.getBirthDate())
                .cinCreationDate(input.getCinCreationDate())
                .cinNumber(input.getCinNumber())
                .cnaps(input.getCnaps())
                .emailPerso(input.getEmailPerso())
                .bruSalary(input.getBrutSalary())
                .emailPro(input.getEmailPro())
                .date_entre(input.getDateEntre())
                .firstName(input.getFirstName())
                .date_sortie(input.getDateSortie())
                .lastName(input.getLastName())
                .phoneNumberList(input.getPhonenumber())
                .sex(input.getSex())
                .matricule(input.getMatricule())
                .photo(Base64.getEncoder().encodeToString(input.getPhoto()))
                .build();
    }
}
