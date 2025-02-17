package com.karan.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;

import com.karan.authservice.entities.UserCreds;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserCredRepository extends JpaRepository<UserCreds, Integer> {

    Optional<UserCreds> findByUserId(String userId);

    Optional<UserCreds> findByUsername(String username);

}
