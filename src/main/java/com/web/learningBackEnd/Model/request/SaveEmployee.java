package com.web.learningBackEnd.Model.request;

import com.web.learningBackEnd.Model.entity.db_test.Employee;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveEmployee {
    private String firstName;
    private String lastName;
    private String birthDate;
    private MultipartFile photo;
    private Employee.SEX sex;
    private String countryCode;
    private String date_entre;
    private String address;
    private String phoneNumber;
    private String emailPerso;
    private String emailPro;
    private String cinNumber;
    private String cinCreationDate;
    private String cnaps;
}
