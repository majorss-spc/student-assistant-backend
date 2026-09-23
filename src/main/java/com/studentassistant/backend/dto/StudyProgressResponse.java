package com.studentassistant.backend.dto;

public class StudyProgressResponse {

    private Long id;
    private String subject;
    private int totalTopics;
    private int completedTopics;
    private double progressPercentage;

    public StudyProgressResponse(
            Long id,
            String subject,
            int totalTopics,
            int completedTopics,
            double progressPercentage) {

        this.id = id;
        this.subject = subject;
        this.totalTopics = totalTopics;
        this.completedTopics = completedTopics;
        this.progressPercentage = progressPercentage;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public int getTotalTopics() {
        return totalTopics;
    }

    public int getCompletedTopics() {
        return completedTopics;
    }

    public double getProgressPercentage() {
        return progressPercentage;
    }
}