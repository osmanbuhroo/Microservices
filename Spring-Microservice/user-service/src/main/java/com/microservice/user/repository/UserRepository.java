package com.microservice.user.repository;

import com.microservice.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByUserId(Long userId);
}
