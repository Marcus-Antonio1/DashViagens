package com.dashviagens.admin.repository;

import java.util.Optional;

import com.dashviagens.admin.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {

    Optional<AdminUser> findByUsername(String username);
}
