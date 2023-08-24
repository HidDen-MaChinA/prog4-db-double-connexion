package com.web.learningBackEnd.Service.facade;

import com.web.learningBackEnd.Controller.utils.InputFormat;
import com.web.learningBackEnd.Controller.utils.UserInformation;
import com.web.learningBackEnd.Mapper.UserInformationMapper;
import com.web.learningBackEnd.Model.entity.db_test.Employee;
import com.web.learningBackEnd.Model.entity.db_test.User;
import com.web.learningBackEnd.Model.request.RequestedEmployee;
import com.web.learningBackEnd.Model.request.SaveEmployee;
import com.web.learningBackEnd.Model.request.UserLogin;
import com.web.learningBackEnd.Repository.employees.CountryCodeRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;


public interface EmployeeManagementFacade {
    List<RequestedEmployee> listAllEmployee(InputFormat by);
    User SignUp(UserInformation input, HttpSession session);

    User SignIn(UserLogin input);
    String ManageEmployee(SaveEmployee input) throws IOException;
    RequestedEmployee getEmployeeDetails(String matricule);
    CountryCodeRepository getcountryCodeInstance();
    User authentifyUser(HttpSession input);
}
