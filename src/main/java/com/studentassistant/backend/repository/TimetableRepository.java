package com.studentassistant.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentassistant.backend.entity.Timetable;
import com.studentassistant.backend.entity.User;

public interface TimetableRepository
        extends JpaRepository<Timetable, Long> {

    List<Timetable> findByUser(User user);
}