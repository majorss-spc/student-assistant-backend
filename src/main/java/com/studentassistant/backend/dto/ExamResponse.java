package com.studentassistant.backend.dto;

public class ExamResponse {

    private Long id;
    private String subject;
    private String examDate;
    private String examTime;
    private String room;

    public ExamResponse(
            Long id,
            String subject,
            String examDate,
            String examTime,
            String room) {

        this.id = id;
        this.subject = subject;
        this.examDate = examDate;
        this.examTime = examTime;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getExamDate() {
        return examDate;
    }

    public String getExamTime() {
        return examTime;
    }

    public String getRoom() {
        return room;
    }
}