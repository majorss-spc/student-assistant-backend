package com.studentassistant.backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AttendanceRequest {

    @NotBlank(message = "Subject is required")
    private String subject;

    @Min(value = 1, message = "Total classes must be at least 1")
    private int totalClasses;

    @Min(value = 0, message = "Attended classes cannot be negative")
    private int attendedClasses;

    public AttendanceRequest() {
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public void setTotalClasses(int totalClasses) {
        this.totalClasses = totalClasses;
    }

    public int getAttendedClasses() {
        return attendedClasses;
    }

    public void setAttendedClasses(int attendedClasses) {
        this.attendedClasses = attendedClasses;
    }
}