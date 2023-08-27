package com.web.learningBackEnd.Model.request;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.Check;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveCompanyInformation {
    private String companyName;
    private String companySlogan;
    private MultipartFile companyLogo;
    private String nif;
    private String stat;
    @Column(length = 10)
    private String number;
    private String address;
    private String email;
}
