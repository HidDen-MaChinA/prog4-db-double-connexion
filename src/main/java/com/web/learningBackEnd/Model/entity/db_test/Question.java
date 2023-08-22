package com.web.learningBackEnd.Model.entity.db_test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    private String id;
    private String question;
    private String useCase;
    //this is something connected to another table
    private String response;
}
