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

import com.studentassistant.backend.dto.ExamRequest;
import com.studentassistant.backend.dto.ExamResponse;
import com.studentassistant.backend.service.ExamService;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @PostMapping
    public ExamResponse addExam(
            @Valid @RequestBody ExamRequest request) {

        return examService.addExam(request);
    }

    @GetMapping
    public List<ExamResponse> getAllExams() {

        return examService.getAllExams();
    }

    @GetMapping("/{id}")
    public ExamResponse getExamById(
            @PathVariable Long id) {

        return examService.getExamById(id);
    }

    @PutMapping("/{id}")
    public ExamResponse updateExam(
            @PathVariable Long id,
            @Valid @RequestBody ExamRequest request) {

        return examService.updateExam(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteExam(
            @PathVariable Long id) {

        examService.deleteExam(id);

        return "Exam deleted successfully";
    }
}