package com.studentassistant.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentassistant.backend.entity.Reminder;
import com.studentassistant.backend.entity.User;

public interface ReminderRepository
        extends JpaRepository<Reminder, Long> {

    List<Reminder> findByUser(User user);
}