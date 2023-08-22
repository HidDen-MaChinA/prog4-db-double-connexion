package com.web.learningBackEnd.Model.request;

import com.web.learningBackEnd.Model.entity.db_test.User;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestedUserInformation {
    private String userName;
    private String token;
    private User.ROLE role;
}
