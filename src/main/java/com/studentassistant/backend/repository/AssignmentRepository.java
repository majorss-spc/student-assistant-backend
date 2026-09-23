package com.studentassistant.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentassistant.backend.entity.Assignment;
import com.studentassistant.backend.entity.User;

public interface AssignmentRepository
        extends JpaRepository<Assignment, Long> {

    List<Assignment> findByUser(User user);
}