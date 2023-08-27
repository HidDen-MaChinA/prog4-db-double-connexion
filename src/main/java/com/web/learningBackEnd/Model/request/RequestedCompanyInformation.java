package com.web.learningBackEnd.Model.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestedCompanyInformation {
    private String companyName;
    private String companySlogan;
    private String companyLogo;
    private String nif;
    private String stat;
    private String email;
    private String address;
    private String number;
}
