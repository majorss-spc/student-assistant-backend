package com.studentassistant.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import com.studentassistant.backend.dto.AssignmentRequest;
import com.studentassistant.backend.dto.AssignmentResponse;
import com.studentassistant.backend.entity.Assignment;
import com.studentassistant.backend.service.AssignmentService;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    public AssignmentController(
            AssignmentService assignmentService) {

        this.assignmentService = assignmentService;
    }

    @PostMapping
    public AssignmentResponse addAssignment(
            @Valid @RequestBody AssignmentRequest request) {

        return assignmentService.addAssignment(request);
    }
    
    @GetMapping
    public List<AssignmentResponse> getAllAssignments() {

        return assignmentService.getAllAssignments();
    }

    @GetMapping("/{id}")
    public AssignmentResponse getAssignmentById(
            @PathVariable Long id) {

        return assignmentService.getAssignmentById(id);
    }

    @PutMapping("/{id}")
    public AssignmentResponse updateAssignment(
            @PathVariable Long id,
            @RequestBody Assignment assignment) {

        return assignmentService.updateAssignment(
                id,
                assignment);
    }

    @DeleteMapping("/{id}")
    public String deleteAssignment(
            @PathVariable Long id) {

        assignmentService.deleteAssignment(id);

        return "Assignment deleted successfully";
    }}
