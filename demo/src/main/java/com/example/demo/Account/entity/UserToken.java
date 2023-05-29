package com.example.demo.Account.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class UserToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userTokenId;
    private String userToken;
    @Getter
    private Long accountId;

}
