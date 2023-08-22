package com.web.learningBackEnd.Model.request;

import com.web.learningBackEnd.Model.entity.db_test.Employee;
import com.web.learningBackEnd.Model.entity.db_test.PhoneNumber;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestedEmployee {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String photo;
    private String matricule;
    private Employee.SEX sex;
    private String address;
    private List<PhoneNumber> phoneNumberList;
    private String emailPerso;
    private String emailPro;
    private Date date_entre;
    private Date date_sortie;
    private String cinNumber;
    private String cinCreationDate;
    private String cnaps;
}
