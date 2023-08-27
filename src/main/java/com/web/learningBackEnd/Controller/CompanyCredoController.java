package com.web.learningBackEnd.Controller;

import com.web.learningBackEnd.Model.entity.db_test.CompanyInformation;
import com.web.learningBackEnd.Model.request.SaveCompanyInformation;
import com.web.learningBackEnd.Repository.employees.CompanyInformationRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;

import java.io.IOException;
import java.util.UUID;

@Controller
@AllArgsConstructor
@Setter
@Getter
public class CompanyCredoController {
    private final CompanyInformationRepository repository;
    @PostMapping("/company/save")
    public String save(@ModelAttribute("company") SaveCompanyInformation input) throws IOException {
        CompanyInformation tosave = CompanyInformation.builder()
                .companyLogo(input.getCompanyLogo().getBytes())
                .companyName(input.getCompanyName())
                .id(UUID.randomUUID().toString())
                .nif(input.getNif())
                .number(input.getNumber())
                .stat(input.getStat())
                .address(input.getAddress())
                .email(input.getEmail())
                .companySlogan(input.getCompanySlogan())
                .build();
        repository.save(tosave);
        return "redirect:/login";
    }
    @GetMapping("/company")
    public String getPage(Model model){
        SaveCompanyInformation input = new SaveCompanyInformation();
        model.addAttribute("company",input);
        return "company";
    }
}
