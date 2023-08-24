package com.web.learningBackEnd.Controller;

import com.web.learningBackEnd.Controller.utils.InputFormat;
import com.web.learningBackEnd.Controller.utils.UserInformation;
import com.web.learningBackEnd.Model.entity.db_test.CountryCode;
import com.web.learningBackEnd.Model.entity.db_test.User;
import com.web.learningBackEnd.Model.request.SaveEmployee;
import com.web.learningBackEnd.Model.request.UserLogin;
import com.web.learningBackEnd.Service.facade.EmployeeManagementFacade;
import jakarta.annotation.Nullable;
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
    private final String redirection = "redirect:/login";
    private boolean verify(HttpSession session){
        User identity = facade.authentifyUser(session);
        return identity==null;
    }
    @GetMapping("/")
    public String test() throws IOException{
        return "redirect:/login";
    }
    @GetMapping("/employees")
    public String GetUsers(Model model, @ModelAttribute("searchEmployee") InputFormat searchBy,HttpSession session) {
        if(verify(session)){
            return "redirect:/login";
        }
        InputFormat search = new InputFormat();
        model.addAttribute("searchEmployee",search);
        model.addAttribute("codes",facade.getcountryCodeInstance().findAll().stream().map(code->code.getCode()).toList());
        model.addAttribute("value",facade.listAllEmployee(searchBy));
        return "employees";
    }
    @GetMapping("/user/{matricule}")
    public String GetUser(Model model, @PathVariable String matricule,HttpSession session) {
        if(verify(session)){
            return "redirect:/login";
        }
        model.addAttribute("user",facade.getEmployeeDetails(matricule));
        return "employee";
    }
    @GetMapping("/createUser")
    public String GetCreateUser(Model model){
        UserLogin toCreate = new UserLogin();
        model.addAttribute("newUser",toCreate);
        return "createUser";
    }
    @PostMapping("/create")
    public String createUser(@ModelAttribute("newUser") UserLogin user) throws IOException{
        facade.SignIn(user);
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String GetLoginPage(Model model){
        UserInformation toSave = new UserInformation();
        model.addAttribute("userDetails",toSave);
        return "login";
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
    @GetMapping("/addEmployee")
    public String AddNewEmployee(Model model,HttpSession session,User user){
        if(verify(session)){
            return "redirect:/login";
        }
        SaveEmployee employee = new SaveEmployee();
        List<CountryCode> countryCode = facade.getcountryCodeInstance().findAll();
        model.addAttribute("countryCode",countryCode);
        model.addAttribute("employee",employee);
        return "add_employee";
    }
    @PostMapping("/save")
    public String Save(@ModelAttribute("employee") SaveEmployee employee,HttpSession session,User user) throws IOException,Exception{
        if(verify(session)){
            return "redirect:/login";
        }
        System.out.println(employee.getCountryCode());
        facade.ManageEmployee(employee);
        return "redirect:/employees";
    }
}
