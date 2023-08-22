package com.web.learningBackEnd.Model.entity.db_test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber {
    @Id
    private String id;
    @Column(length = 10)
    private String value;
    @ManyToOne
    private CountryCode countryCode;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonIgnore
    private Employee user;
}
