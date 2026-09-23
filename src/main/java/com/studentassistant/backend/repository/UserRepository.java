package com.studentassistant.backend.repository;

import com.studentassistant.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);//login time email check

    boolean existsByEmail(String email);//signup time existing email check
}