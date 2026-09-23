package com.studentassistant.backend.dto;

public class DashboardResponse {

    private long totalAssignments;
    private long completedAssignments;

    private long totalAttendanceSubjects;
    private double averageAttendance;

    private long totalStudyProgress;
    private double averageStudyProgress;

    private long upcomingExams;

    public DashboardResponse(
            long totalAssignments,
            long completedAssignments,
            long totalAttendanceSubjects,
            double averageAttendance,
            long totalStudyProgress,
            double averageStudyProgress,
            long upcomingExams) {

        this.totalAssignments = totalAssignments;
        this.completedAssignments = completedAssignments;
        this.totalAttendanceSubjects = totalAttendanceSubjects;
        this.averageAttendance = averageAttendance;
        this.totalStudyProgress = totalStudyProgress;
        this.averageStudyProgress = averageStudyProgress;
        this.upcomingExams = upcomingExams;
    }

    public long getTotalAssignments() {
        return totalAssignments;
    }

    public long getCompletedAssignments() {
        return completedAssignments;
    }

    public long getTotalAttendanceSubjects() {
        return totalAttendanceSubjects;
    }

    public double getAverageAttendance() {
        return averageAttendance;
    }

    public long getTotalStudyProgress() {
        return totalStudyProgress;
    }

    public double getAverageStudyProgress() {
        return averageStudyProgress;
    }

    public long getUpcomingExams() {
        return upcomingExams;
    }
}