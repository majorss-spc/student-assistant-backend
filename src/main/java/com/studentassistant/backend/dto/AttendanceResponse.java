package com.studentassistant.backend.dto;

public class AttendanceResponse {

    private Long id;
    private String subject;
    private int totalClasses;
    private int attendedClasses;
    private double attendancePercentage;

    public AttendanceResponse(
            Long id,
            String subject,
            int totalClasses,
            int attendedClasses,
            double attendancePercentage) {

        this.id = id;
        this.subject = subject;
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
        this.attendancePercentage = attendancePercentage;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public int getTotalClasses() {
        return totalClasses;
    }

    public int getAttendedClasses() {
        return attendedClasses;
    }

    public double getAttendancePercentage() {
        return attendancePercentage;
    }
}