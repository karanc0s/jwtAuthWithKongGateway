package com.karan.authservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usercreds")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreds extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String userId;

    private String username;

    private String password;

}
