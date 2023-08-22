package com.web.learningBackEnd.Model.entity.db_cnaps;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class user {
    @Id
    private String id;
}
