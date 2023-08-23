package com.web.learningBackEnd.Repository.employees.Impl;

import com.web.learningBackEnd.Model.entity.db_test.Employee;
import com.web.learningBackEnd.Model.request.DatePlage;
import com.web.learningBackEnd.Repository.employees.CustomRepositoryForFiltering;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Repository
public class CustomRepositoryForFilteringImpl implements CustomRepositoryForFiltering  {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> filterEmployee(String country_code, String lastName, String firstName, String birthday, DatePlage start, DatePlage end, Employee.SEX sex) {
        String MyQuery = this.createSuitableQueryForFiltering(country_code,lastName,firstName,birthday,start,end,sex);
        Query query = entityManager.createNativeQuery(MyQuery, Employee.class);
        List<Employee> employees = query.getResultList();
        return employees;
    }
    private String createSuitableQueryForFiltering(String country_code, String lastName, String firstName, String birthday, DatePlage start, DatePlage end, Employee.SEX sex){
        StringBuilder output = new StringBuilder("SELECT * FROM employee WHERE ");
        AtomicBoolean added = new AtomicBoolean(false);
        System.out.println(firstName);
        ConditionBlock before = ()->{
            if(added.get()){
                output.append(" AND ");
            }
            else{
                added.set(true);
            }
        };
        IfWithCondition(
                lastName!=null,
                ()->{output.append("lastname like '%").append(lastName).append("%'");},
                before
        );
        IfWithCondition(
                firstName!=null,
                ()->{output.append("firstname like '%").append(firstName).append("%'");},
                before
        );
        IfWithCondition(
                birthday!=null,
                ()->{output.append("birthdate = '").append(birthday).append("'");},
                before
        );
        IfWithCondition(
                sex!=null,
                ()->{output.append("sex = '").append(sex.name()).append("'");},
                before
        );
        IfWithCondition(
                start!=null,
                ()->{
                    output.append("dateentre BETWEEN '").append(start.getFrom()).append("' AND '").append(start.getTo()).append("'");},
                before
        );
        IfWithCondition(
                end!=null,
                ()->{output.append("dateentre BETWEEN '").append(end.getFrom()).append("' AND '").append(end.getTo()).append("'");},
                before
        );

        System.out.println(output.toString());
        return output.toString();
    }

    @FunctionalInterface
    private interface ConditionBlock {
        void execute();
    }

    private void IfWithCondition(boolean condition,ConditionBlock conditionBlock,ConditionBlock before){
        if(condition){
            before.execute();
            conditionBlock.execute();
        }
    }
}
