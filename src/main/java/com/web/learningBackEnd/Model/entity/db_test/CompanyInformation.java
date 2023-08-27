package com.web.learningBackEnd.Model.entity.db_test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CompanyInformation {
    @Id
    private String id;
    private String companyName;
    private String companySlogan;
    private byte[] companyLogo;
    private String nif;
    private String number;
    private String stat;
    private String address;
    private String email;
}
