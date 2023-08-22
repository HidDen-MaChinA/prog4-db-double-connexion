package com.web.learningBackEnd.Mapper.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConvertDate {
    private int year;
    private int day;
    private int month;
    public Date getSqlDateFormat(String date){
        return Date.valueOf(date);
    }
}
