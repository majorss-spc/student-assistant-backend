package com.studentassistant.backend.dto;

import java.time.LocalDate;

public class AssignmentResponse {

    private Long id;
    private String title;
    private String subject;
    private LocalDate dueDate;
    private boolean completed;
    private String description;

    public AssignmentResponse() {
    }

    public AssignmentResponse(
            Long id,
            String title,
            String subject,
            LocalDate dueDate,
            boolean completed,
            String description) {

        this.id = id;
        this.title = title;
        this.subject = subject;
        this.dueDate = dueDate;
        this.completed = completed;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getDescription() {
        return description;
    }
}