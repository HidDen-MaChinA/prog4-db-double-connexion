package com.web.learningBackEnd.Model.entity.db_test;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "\"user\"")
@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String id;
    private String username;
    private ROLE role;
    private String password;
    @Column(unique = true)
    private String token;
    public enum ROLE{
        manager,root
    }
}
