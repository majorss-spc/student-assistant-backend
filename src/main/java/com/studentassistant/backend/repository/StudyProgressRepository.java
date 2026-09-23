package com.studentassistant.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentassistant.backend.entity.StudyProgress;
import com.studentassistant.backend.entity.User;

public interface StudyProgressRepository
        extends JpaRepository<StudyProgress, Long> {

    List<StudyProgress> findByUser(User user);
}