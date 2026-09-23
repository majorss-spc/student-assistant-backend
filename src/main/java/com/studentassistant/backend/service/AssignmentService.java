package com.studentassistant.backend.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.studentassistant.backend.dto.AssignmentRequest;
import com.studentassistant.backend.dto.AssignmentResponse;
import com.studentassistant.backend.entity.Assignment;
import com.studentassistant.backend.entity.User;
import com.studentassistant.backend.repository.AssignmentRepository;
import com.studentassistant.backend.repository.UserRepository;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;

    public AssignmentService(
            AssignmentRepository assignmentRepository,
            UserRepository userRepository) {

        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
    }

    public AssignmentResponse addAssignment(
            AssignmentRequest request) {

        User user = getCurrentUser();

        Assignment assignment = new Assignment();

        assignment.setTitle(request.getTitle());
        assignment.setSubject(request.getSubject());
        assignment.setDueDate(request.getDueDate());
        assignment.setCompleted(request.isCompleted());
        assignment.setDescription(request.getDescription());

        assignment.setUser(user);

        Assignment savedAssignment =
                assignmentRepository.save(assignment);

        return convertToResponse(savedAssignment);
    }

    public List<AssignmentResponse> getAllAssignments() {

        User user = getCurrentUser();

        return assignmentRepository.findByUser(user)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public AssignmentResponse getAssignmentById(Long id) {

        User user = getCurrentUser();

        Assignment assignment =
                assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Assignment not found"));

        if (!assignment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException(
                    "You cannot access this assignment");
        }

        return convertToResponse(assignment);
    }

    public AssignmentResponse updateAssignment(
            Long id,
            Assignment updatedAssignment) {

        User user = getCurrentUser();

        Assignment assignment =
                assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Assignment not found"));

        if (!assignment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException(
                    "You cannot update this assignment");
        }

        assignment.setTitle(updatedAssignment.getTitle());
        assignment.setSubject(updatedAssignment.getSubject());
        assignment.setDueDate(updatedAssignment.getDueDate());
        assignment.setCompleted(updatedAssignment.isCompleted());
        assignment.setDescription(
                updatedAssignment.getDescription());

        Assignment savedAssignment =
                assignmentRepository.save(assignment);

        return convertToResponse(savedAssignment);
    }

    public void deleteAssignment(Long id) {

        User user = getCurrentUser();

        Assignment assignment =
                assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Assignment not found"));

        if (!assignment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException(
                    "You cannot delete this assignment");
        }

        assignmentRepository.delete(assignment);
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

    private AssignmentResponse convertToResponse(
            Assignment assignment) {

        return new AssignmentResponse(
                assignment.getId(),
                assignment.getTitle(),
                assignment.getSubject(),
                assignment.getDueDate(),
                assignment.isCompleted(),
                assignment.getDescription()
        );
    }
}