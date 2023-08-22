package com.web.learningBackEnd.Model.entity.db_test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Evaluation {
    @Id
    private String id;
    private String name;

    //this is something connected to another table
    private String question;
}
