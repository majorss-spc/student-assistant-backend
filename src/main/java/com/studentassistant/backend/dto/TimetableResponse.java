package com.studentassistant.backend.dto;

public class TimetableResponse {

    private Long id;
    private String day;
    private String subject;
    private String startTime;
    private String endTime;
    private String room;

    public TimetableResponse(
            Long id,
            String day,
            String subject,
            String startTime,
            String endTime,
            String room) {

        this.id = id;
        this.day = day;
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getSubject() {
        return subject;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getRoom() {
        return room;
    }
}