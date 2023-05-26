package com.example.demo.Account;

import com.example.demo.Account.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken,Long> {
    public Optional<UserToken> findByUserToken(String userToken);
}
