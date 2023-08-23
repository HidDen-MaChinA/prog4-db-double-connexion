package com.web.learningBackEnd.Controller;

import com.web.learningBackEnd.Controller.utils.InputFormat;
import com.web.learningBackEnd.Controller.utils.UserInformation;
import com.web.learningBackEnd.Mapper.EmployeeMapper;
import com.web.learningBackEnd.Mapper.UserInformationMapper;
import com.web.learningBackEnd.Model.entity.db_test.CountryCode;
import com.web.learningBackEnd.Model.entity.db_test.User;
import com.web.learningBackEnd.Model.request.SaveEmployee;
import com.web.learningBackEnd.Model.request.SaveUserInformation;
import com.web.learningBackEnd.Repository.employees.CountryCodeRepository;
import com.web.learningBackEnd.Service.SecurityService;
import com.web.learningBackEnd.Service.EmployeeService;
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
    private final EmployeeMapper mapper;
    private final UserInformationMapper userInformationMapper;
    private final EmployeeService service;
    private final SecurityService security;
    private final CountryCodeRepository countryCodeRepository;

    @GetMapping("/")
    public String test() throws IOException{
        return "redirect:/login";
    }
    @GetMapping("/employees")
    public String GetUsers(Model model, @ModelAttribute("searchEmployee") InputFormat searchBy,HttpSession session) {
        User identity = security.getUserByToken(session);
        if(identity==null){
            System.out.println("you've been redirected to login");
            return "redirect:/login";
        }
        searchBy.clean();
        InputFormat search = new InputFormat();
        model.addAttribute("searchEmployee",search);
        model.addAttribute("codes",countryCodeRepository.findAll().stream().map(code->code.getCode()).toList());
        if(!searchBy.IsEmpty()){
            model.addAttribute("value",service.filterEmployee(searchBy.getCountryCode(),searchBy.getLastName(),searchBy.getFirstName(),searchBy.getBirthday(),searchBy.getStart(),searchBy.getEnd(), searchBy.getSex()));
            return "employees";
        }
        model.addAttribute("value",service.getAll());
        return "employees";
    }
    @GetMapping("/user/{matricule}")
    public String GetUser(Model model, @PathVariable String matricule,HttpSession session) {
        User identity = security.getUserByToken(session);
        if(identity==null){
            return "redirect:/login";
        }
        model.addAttribute("user",service.getEmployeeByMatricule(matricule));
        return "employee";
    }
    @GetMapping("/createUser")
    public String GetCreateUser(Model model){
        SaveUserInformation toCreate = new SaveUserInformation();
        model.addAttribute("newUser",toCreate);
        return "createUser";
    }
    @PostMapping("/create")
    public String createUser(@ModelAttribute("newUser") SaveUserInformation user) throws IOException{
        security.saveUser(userInformationMapper.toEntity(user));
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
            System.out.println("not data provided");
            return "redirect:/login";
        }
        User identity = security.authentifyUserOnLogin(details);
        if(identity==null){
            System.out.println("fuck does not exist");
            return "redirect:/login";
        }
        security.setTokenSession(identity,session);
        return "redirect:/employees";
    }
    @GetMapping("/addEmployee")
    public String AddNewEmployee(Model model,HttpSession session){
        User user = security.getUserByToken(session);
        if(user ==null){
            return "redirect:/login";
        }
        SaveEmployee employee = new SaveEmployee();
        List<CountryCode> countryCode = countryCodeRepository.findAll();
        model.addAttribute("countryCode",countryCode);
        model.addAttribute("employee",employee);
        return "add_employee";
    }
    @PostMapping("/save")
    public String Save(@ModelAttribute("employee") SaveEmployee user,HttpSession session) throws IOException,Exception{
        User identity = security.getUserByToken(session);
        if(identity==null){
            return "redirect:/login";
        }
        System.out.println(user.getCountryCode());
        service.saveEmployee(mapper.toEntity(user));
        return "redirect:/employees";
    }
}
