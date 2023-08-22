package com.web.learningBackEnd.Service;

import com.web.learningBackEnd.Controller.utils.UserInformation;
import com.web.learningBackEnd.Model.entity.db_test.User;
import com.web.learningBackEnd.Repository.employees.UsersRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Setter
@Getter
@AllArgsConstructor
public class SecurityService {
    private final UsersRepository repository;

    public User authentifyUserOnLogin(UserInformation detail){
        User test =  repository.getUserByUsernameAndPassword(detail.getUserName(),detail.getPassword());
        return test;
    }
    public void setTokenSession(User user, HttpSession session){
        String token = UUID.randomUUID().toString();
        session.setAttribute("JSESSIONID",token);
        repository.updateUserToken(token, user.getPassword());
    }
    public User getUserByToken(HttpSession session){
        Object UserToken = session.getAttribute("JSESSIONID");
        if (UserToken==null){
            System.out.println("session inaccessible");
            return null;
        }
        return repository.getUserByToken(UserToken.toString());
    }
    public User saveUser(User input){
        return repository.save(input);
    }
}
