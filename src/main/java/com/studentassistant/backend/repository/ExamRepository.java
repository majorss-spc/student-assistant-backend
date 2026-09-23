package com.studentassistant.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentassistant.backend.entity.Exam;
import com.studentassistant.backend.entity.User;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByUser(User user);
}