package com.studentassistant.backend.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.studentassistant.backend.dto.AttendanceRequest;
import com.studentassistant.backend.dto.AttendanceResponse;
import com.studentassistant.backend.entity.Attendance;
import com.studentassistant.backend.entity.User;
import com.studentassistant.backend.repository.AttendanceRepository;
import com.studentassistant.backend.repository.UserRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository,
            UserRepository userRepository) {

        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }

    public AttendanceResponse addAttendance(
            AttendanceRequest request) {

        User user = getCurrentUser();

        if (request.getAttendedClasses()
                > request.getTotalClasses()) {

            throw new RuntimeException(
                    "Attended classes cannot be greater than total classes");
        }

        Attendance attendance = new Attendance();

        attendance.setSubject(request.getSubject());
        attendance.setTotalClasses(
                request.getTotalClasses());
        attendance.setAttendedClasses(
                request.getAttendedClasses());

        attendance.setUser(user);

        Attendance savedAttendance =
                attendanceRepository.save(attendance);

        return convertToResponse(savedAttendance);
    }

    public List<AttendanceResponse> getAllAttendance() {

        User user = getCurrentUser();

        return attendanceRepository.findByUser(user)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public AttendanceResponse getAttendanceById(Long id) {

        User user = getCurrentUser();

        Attendance attendance =
                attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attendance not found"));

        if (!attendance.getUser().getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot access this attendance");
        }

        return convertToResponse(attendance);
    }

    public AttendanceResponse updateAttendance(
            Long id,
            AttendanceRequest request) {

        User user = getCurrentUser();

        Attendance attendance =
                attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attendance not found"));

        if (!attendance.getUser().getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot update this attendance");
        }

        if (request.getAttendedClasses()
                > request.getTotalClasses()) {

            throw new RuntimeException(
                    "Attended classes cannot be greater than total classes");
        }

        attendance.setSubject(request.getSubject());
        attendance.setTotalClasses(
                request.getTotalClasses());
        attendance.setAttendedClasses(
                request.getAttendedClasses());

        Attendance savedAttendance =
                attendanceRepository.save(attendance);

        return convertToResponse(savedAttendance);
    }

    public void deleteAttendance(Long id) {

        User user = getCurrentUser();

        Attendance attendance =
                attendanceRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Attendance not found"));

        if (!attendance.getUser().getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot delete this attendance");
        }

        attendanceRepository.delete(attendance);
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));
    }

    private AttendanceResponse convertToResponse(
            Attendance attendance) {

        double percentage =
                ((double) attendance.getAttendedClasses()
                        / attendance.getTotalClasses())
                        * 100;

        return new AttendanceResponse(
                attendance.getId(),
                attendance.getSubject(),
                attendance.getTotalClasses(),
                attendance.getAttendedClasses(),
                percentage
        );
    }
}