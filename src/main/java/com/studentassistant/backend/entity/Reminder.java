package com.studentassistant.backend.entity;

// Date store karne ke liye
import java.time.LocalDate;

// Time store karne ke liye
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity // Ye class database table banegi
public class Reminder {

    // Primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Reminder ka title
    private String title;

    // Reminder ki date
    private LocalDate reminderDate;

    // Reminder ka time
    private LocalTime reminderTime;

    // Optional description
    private String description;

    // Reminder complete hua ya nahi
    private boolean completed = false;

    // Reminder kis user ka hai
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // JPA ke liye empty constructor
    public Reminder() {
    }

    // ID getter
    public Long getId() {
        return id;
    }

    // ID setter
    public void setId(Long id) {
        this.id = id;
    }

    // Title getter
    public String getTitle() {
        return title;
    }

    // Title setter
    public void setTitle(String title) {
        this.title = title;
    }

    // Date getter
    public LocalDate getReminderDate() {
        return reminderDate;
    }

    // Date setter
    public void setReminderDate(LocalDate reminderDate) {
        this.reminderDate = reminderDate;
    }

    // Time getter
    public LocalTime getReminderTime() {
        return reminderTime;
    }

    // Time setter
    public void setReminderTime(LocalTime reminderTime) {
        this.reminderTime = reminderTime;
    }

    // Description getter
    public String getDescription() {
        return description;
    }

    // Description setter
    public void setDescription(String description) {
        this.description = description;
    }

    // Completed getter
    public boolean isCompleted() {
        return completed;
    }

    // Completed setter
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // User getter
    public User getUser() {
        return user;
    }

    // User setter
    public void setUser(User user) {
        this.user = user;
    }
}