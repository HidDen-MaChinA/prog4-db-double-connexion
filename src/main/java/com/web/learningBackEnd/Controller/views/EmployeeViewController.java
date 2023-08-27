package com.web.learningBackEnd.Controller.views;

import com.web.learningBackEnd.Controller.utils.InputFormat;
import com.web.learningBackEnd.Controller.utils.ReplicateFunction;
import com.web.learningBackEnd.Model.entity.db_test.CountryCode;
import com.web.learningBackEnd.Model.entity.db_test.User;
import com.web.learningBackEnd.Model.request.SaveEmployee;
import com.web.learningBackEnd.Model.request.UserLogin;
import com.web.learningBackEnd.Service.facade.EmployeeManagementFacade;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@AllArgsConstructor
public class EmployeeViewController {
    private final EmployeeManagementFacade facade;
    private final ReplicateFunction replicate;
    @GetMapping("/employees")
    public String GetUsers(Model model, @ModelAttribute("searchEmployee") InputFormat searchBy, HttpSession session) {
        if(replicate.verify(session)){
            return "redirect:/login";
        }
        InputFormat search = new InputFormat();
        model.addAttribute("searchEmployee",search);
        model.addAttribute("codes",facade.getcountryCodeInstance().findAll().stream().map(code->code.getCode()).toList());
        model.addAttribute("value",facade.listAllEmployee(searchBy));
        return "employees";
    }
    @GetMapping("/employee/{matricule}")
    public String GetUser(Model model, @PathVariable String matricule, HttpSession session) {
        if(replicate.verify(session)){
            return "redirect:/login";
        }
        model.addAttribute("employee",facade.getEmployeeDetails(matricule));
        return "employee";
    }
    @GetMapping("/addEmployee")
    public String AddNewEmployee(Model model, HttpSession session, User user){
        if(replicate.verify(session)){
            return "redirect:/login";
        }
        SaveEmployee employee = new SaveEmployee();
        List<CountryCode> countryCode = facade.getcountryCodeInstance().findAll();
        model.addAttribute("countryCode",countryCode);
        model.addAttribute("employee",employee);
        return "add_employee";
    }
}
