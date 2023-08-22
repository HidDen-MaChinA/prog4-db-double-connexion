package com.web.learningBackEnd.Model.entity.db_test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyInformation {
    @Id
    private String id;
    private String companyName;
    private String companySlogan;
    private byte[] companyLogo;
}
