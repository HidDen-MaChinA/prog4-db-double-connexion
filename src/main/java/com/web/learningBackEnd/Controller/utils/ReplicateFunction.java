package com.web.learningBackEnd.Controller.utils;

import com.web.learningBackEnd.Model.entity.db_test.User;
import com.web.learningBackEnd.Service.facade.EmployeeManagementFacade;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@AllArgsConstructor
public class ReplicateFunction {
    private final EmployeeManagementFacade facade;
    public boolean verify(HttpSession session){
        User identity = facade.authentifyUser(session);
        return identity==null;
    }
}
