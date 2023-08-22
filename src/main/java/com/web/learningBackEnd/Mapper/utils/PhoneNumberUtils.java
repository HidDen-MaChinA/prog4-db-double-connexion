package com.web.learningBackEnd.Mapper.utils;

import com.web.learningBackEnd.Repository.employees.PhoneNumberRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Getter
@Setter
@AllArgsConstructor
public class PhoneNumberUtils {
    private final PhoneNumberRepository repository;
    public String approve(String input) throws RuntimeException {
        if(input.length()==10){
            return input;
        }
        throw new RuntimeException("phone number not allowed");
    }
    public List<String> TransformStringPhoneNumber(String input){
        return Arrays.stream(input.split(",")).map(this::approve).toList();
    };
}
