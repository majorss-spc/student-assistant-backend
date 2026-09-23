package com.studentassistant.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentassistant.backend.entity.Attendance;
import com.studentassistant.backend.entity.User;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    List<Attendance> findByUser(User user);
}