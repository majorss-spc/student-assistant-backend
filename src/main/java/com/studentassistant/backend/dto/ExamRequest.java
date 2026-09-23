package com.studentassistant.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class ExamRequest {

    @NotBlank(message = "Subject is required")
    private String subject;

    @NotBlank(message = "Exam date is required")
    private String examDate;

    @NotBlank(message = "Exam time is required")
    private String examTime;

    private String room;

    public ExamRequest() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public void setExamTime(String examTime) {
        this.examTime = examTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}