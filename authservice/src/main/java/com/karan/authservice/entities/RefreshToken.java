package com.karan.authservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "refreshtokens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    private String userId;

    private String refreshToken;

    private boolean isRevoked;


}
