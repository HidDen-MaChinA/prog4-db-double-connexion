package com.web.learningBackEnd.Controller;

import com.lowagie.text.DocumentException;
import com.web.learningBackEnd.Controller.utils.InputFormat;
import com.web.learningBackEnd.Controller.utils.ReplicateFunction;
import com.web.learningBackEnd.Controller.utils.UserInformation;
import com.web.learningBackEnd.Model.entity.db_test.CountryCode;
import com.web.learningBackEnd.Model.entity.db_test.User;
import com.web.learningBackEnd.Model.request.SaveEmployee;
import com.web.learningBackEnd.Model.request.UserLogin;
import com.web.learningBackEnd.Service.facade.EmployeeManagementFacade;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
@Setter
@Getter
public class EmployeeController {
    private final EmployeeManagementFacade facade;
    private final ReplicateFunction replicate;
    private final String redirection = "redirect:/login";
    @GetMapping("/")
    public String test() throws IOException{
        return "redirect:/login";
    }
    @PostMapping("/create")
    public String createUser(@ModelAttribute("newUser") UserLogin user) throws IOException{
        facade.SignIn(user);
        return "redirect:/login";
    }
    @GetMapping("/authentify")
    public String AuthentifyUser(@ModelAttribute("userDetails") @Nullable UserInformation details, HttpSession session){
        if(details==null){
            return "redirect:/login";
        }
        User identity = facade.SignUp(details,session);
        if(identity==null){
            return "redirect:/login";
        }
        return "redirect:/employees";
    }
    @GetMapping("/employee/pdf/{employee}")
    public void getPdf(@PathVariable String employee, HttpServletResponse response) throws DocumentException,IOException {
        facade.getPdf(employee,response.getOutputStream());
    }
    @PostMapping("/save")
    public String Save(@ModelAttribute("employee") SaveEmployee employee,HttpSession session,User user) throws IOException,Exception{
        if(replicate.verify(session)){
            return "redirect:/login";
        }
        System.out.println(employee.getCountryCode());
        facade.ManageEmployee(employee);
        return "redirect:/employees";
    }
}
