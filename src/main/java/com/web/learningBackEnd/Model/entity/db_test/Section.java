package com.web.learningBackEnd.Model.entity.db_test;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Section {
    @Id
    private String id;
    private String name;
    private String detail;
    @ManyToOne
    private Employee user;
}
