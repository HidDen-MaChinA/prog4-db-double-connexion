package com.web.learningBackEnd.Mapper;

import com.web.learningBackEnd.Model.entity.db_test.User;
import com.web.learningBackEnd.Model.request.RequestedUserInformation;
import com.web.learningBackEnd.Model.request.SaveUserInformation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
@Getter
@Setter
public class UserInformationMapper {
    public User toEntity(SaveUserInformation input){
        return User.builder()
                .password(input.getPassword())
                .username(input.getUserName())
                .id(UUID.randomUUID().toString()).build();
    }

    public RequestedUserInformation toRest(User input) {
        return RequestedUserInformation.builder()
                .role(input.getRole())
                .userName(input.getUsername())
                .token(input.getToken())
                .build();
    }
}
