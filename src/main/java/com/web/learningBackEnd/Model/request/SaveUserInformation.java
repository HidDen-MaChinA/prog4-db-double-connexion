package com.web.learningBackEnd.Model.request;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserInformation {
    private String userName;
    private String password;
}
