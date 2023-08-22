package com.web.learningBackEnd.Model.entity.db_cnaps;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class user {
    @Id
    private String id;
}
