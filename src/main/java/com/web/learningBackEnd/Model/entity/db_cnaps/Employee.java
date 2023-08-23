package com.web.learningBackEnd.Model.entity.db_cnaps;

import com.web.learningBackEnd.Model.entity.db_test.PhoneNumber;
import com.web.learningBackEnd.Model.entity.db_test.Section;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Employee {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private byte[] photo;
    private com.web.learningBackEnd.Model.entity.db_test.Employee.SEX sex;
    private String address;
    private String emailPerso;
    private String emailPro;
    private String cinNumber;
    private Date dateEntre;
    private Date dateSortie;
    private String cinCreationDate;
    private String cnaps;
    public enum SEX{
        H,F
    }
}
