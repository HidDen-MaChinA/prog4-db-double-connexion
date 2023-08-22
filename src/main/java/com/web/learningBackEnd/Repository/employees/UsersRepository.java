package com.web.learningBackEnd.Repository.employees;

import com.web.learningBackEnd.Model.entity.db_test.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@EnableJpaRepositories(entityManagerFactoryRef = "employeeDb")
public interface UsersRepository extends JpaRepository<User,String> {
    User getUserByToken(String token);
    User getUserByUsernameAndPassword(String username, String password);
    @Modifying
    @Query(nativeQuery = true, value = "update \"user\" set token=:token where password=:password")
    @Transactional
    void updateUserToken(String token,String password);
}