package com.studentassistant.backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.studentassistant.backend.dto.DashboardResponse;
import com.studentassistant.backend.entity.Assignment;
import com.studentassistant.backend.entity.Attendance;
import com.studentassistant.backend.entity.Exam;
import com.studentassistant.backend.entity.StudyProgress;
import com.studentassistant.backend.entity.User;
import com.studentassistant.backend.repository.AssignmentRepository;
import com.studentassistant.backend.repository.AttendanceRepository;
import com.studentassistant.backend.repository.ExamRepository;
import com.studentassistant.backend.repository.StudyProgressRepository;
import com.studentassistant.backend.repository.UserRepository;

@Service
public class DashboardService {

    private final AssignmentRepository assignmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final StudyProgressRepository studyProgressRepository;
    private final ExamRepository examRepository;
    private final UserRepository userRepository;

    public DashboardService(
            AssignmentRepository assignmentRepository,
            AttendanceRepository attendanceRepository,
            StudyProgressRepository studyProgressRepository,
            ExamRepository examRepository,
            UserRepository userRepository) {

        this.assignmentRepository = assignmentRepository;
        this.attendanceRepository = attendanceRepository;
        this.studyProgressRepository = studyProgressRepository;
        this.examRepository = examRepository;
        this.userRepository = userRepository;
    }

    public DashboardResponse getDashboard() {

        User user = getCurrentUser();

        List<Assignment> assignments =
                assignmentRepository.findByUser(user);

        List<Attendance> attendanceList =
                attendanceRepository.findByUser(user);

        List<StudyProgress> progressList =
                studyProgressRepository.findByUser(user);

        List<Exam> exams =
                examRepository.findByUser(user);

        long totalAssignments = assignments.size();

        long completedAssignments = assignments.stream()
                .filter(Assignment::isCompleted)
                .count();

        long totalAttendanceSubjects =
                attendanceList.size();

        double averageAttendance =
                attendanceList.stream()
                        .mapToDouble(attendance ->
                                ((double) attendance.getAttendedClasses()
                                        / attendance.getTotalClasses()) * 100)
                        .average()
                        .orElse(0.0);

        long totalStudyProgress =
                progressList.size();

        double averageStudyProgress =
                progressList.stream()
                        .mapToDouble(progress ->
                                ((double) progress.getCompletedTopics()
                                        / progress.getTotalTopics()) * 100)
                        .average()
                        .orElse(0.0);

        long upcomingExams = exams.stream()
                .filter(exam -> {
                    try {
                        LocalDate examDate =
                                LocalDate.parse(exam.getExamDate());

                        return !examDate.isBefore(LocalDate.now());

                    } catch (Exception e) {
                        return false;
                    }
                })
                .count();

        return new DashboardResponse(
                totalAssignments,
                completedAssignments,
                totalAttendanceSubjects,
                averageAttendance,
                totalStudyProgress,
                averageStudyProgress,
                upcomingExams
        );
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }
}