package com.web.learningBackEnd.Model.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatePlage {
    private LocalDate from;
    private LocalDate to;
}
