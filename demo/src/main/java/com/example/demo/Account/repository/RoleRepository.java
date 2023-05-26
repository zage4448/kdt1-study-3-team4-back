package com.example.demo.Account.repository;

import com.example.demo.Account.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
