package com.studentassistant.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class StudyProgressRequest {

    @NotBlank(message = "Subject is required")
    private String subject;

    @Min(value = 1, message = "Total topics must be at least 1")
    private int totalTopics;

    @Min(value = 0, message = "Completed topics cannot be negative")
    private int completedTopics;

    public StudyProgressRequest() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTotalTopics() {
        return totalTopics;
    }

    public void setTotalTopics(int totalTopics) {
        this.totalTopics = totalTopics;
    }

    public int getCompletedTopics() {
        return completedTopics;
    }

    public void setCompletedTopics(int completedTopics) {
        this.completedTopics = completedTopics;
    }
}