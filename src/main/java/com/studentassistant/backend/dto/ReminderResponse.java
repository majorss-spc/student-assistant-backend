package com.studentassistant.backend.dto;

// Date ke liye
import java.time.LocalDate;

// Time ke liye
import java.time.LocalTime;

public class ReminderResponse {

    // Reminder ID
    private Long id;

    // Reminder title
    private String title;

    // Reminder date
    private LocalDate reminderDate;

    // Reminder time
    private LocalTime reminderTime;

    // Description
    private String description;

    // Completed status
    private boolean completed;

    // Constructor
    public ReminderResponse(
            Long id,
            String title,
            LocalDate reminderDate,
            LocalTime reminderTime,
            String description,
            boolean completed) {

        this.id = id;
        this.title = title;
        this.reminderDate = reminderDate;
        this.reminderTime = reminderTime;
        this.description = description;
        this.completed = completed;
    }

    // ID getter
    public Long getId() {
        return id;
    }

    // Title getter
    public String getTitle() {
        return title;
    }

    // Date getter
    public LocalDate getReminderDate() {
        return reminderDate;
    }

    // Time getter
    public LocalTime getReminderTime() {
        return reminderTime;
    }

    // Description getter
    public String getDescription() {
        return description;
    }

    // Completed getter
    public boolean isCompleted() {
        return completed;
    }
}