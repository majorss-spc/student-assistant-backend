package com.studentassistant.backend.dto;

// Date ke liye
import java.time.LocalDate;

// Time ke liye
import java.time.LocalTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReminderRequest {

    // Reminder title mandatory hai
    @NotBlank(message = "Title is required")
    private String title;

    // Reminder date mandatory hai
    @NotNull(message = "Reminder date is required")
    private LocalDate reminderDate;

    // Reminder time mandatory hai
    @NotNull(message = "Reminder time is required")
    private LocalTime reminderTime;

    // Description optional hai
    private String description;

    // Completed status
    private boolean completed = false;

    // Empty constructor
    public ReminderRequest() {
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
}