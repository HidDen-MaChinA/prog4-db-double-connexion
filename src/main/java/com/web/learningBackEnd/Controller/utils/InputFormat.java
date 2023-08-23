package com.web.learningBackEnd.Controller.utils;

import com.web.learningBackEnd.Model.entity.db_test.Employee;
import com.web.learningBackEnd.Model.request.DatePlage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.intellij.lang.annotations.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class InputFormat {
    private String firstName;
    private String lastName;
    private String birthday;
    private String countryCode;
    private DatePlage start;
    private DatePlage end;
    private Integer sex;

    public void clean() {
        this.firstName = checker(firstName);
        this.lastName = checker(lastName);
        this.birthday = checker(birthday);
        this.start = checker(start);
        this.end = checker(end);
        this.countryCode = checker(countryCode);
        this.sex = sex;
    }
    private String checker(String input){
        try{
            return input.isEmpty() ? null : input;
        }catch (Exception e){
            return null;
        }
    }

    private DatePlage checker(DatePlage input){
        try{
            return input.getFrom().toString().isEmpty() || input.getTo().toString().isEmpty() ? null : input;
        }catch (Exception e){
            return null;
        }
    }

    public boolean IsEmpty(){
        return firstName == null && lastName == null && start == null && end == null && birthday == null && countryCode == null && sex == null;
    }
}
