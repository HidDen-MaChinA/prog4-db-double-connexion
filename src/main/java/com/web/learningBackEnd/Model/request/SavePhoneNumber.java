package com.web.learningBackEnd.Model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavePhoneNumber {
    private String value;
    private String country;
}
