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

import com.studentassistant.backend.dto.StudyProgressRequest;
import com.studentassistant.backend.dto.StudyProgressResponse;
import com.studentassistant.backend.service.StudyProgressService;

@RestController
@RequestMapping("/api/study-progress")
public class StudyProgressController {

    private final StudyProgressService studyProgressService;

    public StudyProgressController(
            StudyProgressService studyProgressService) {

        this.studyProgressService = studyProgressService;
    }

    @PostMapping
    public StudyProgressResponse addProgress(
            @Valid @RequestBody StudyProgressRequest request) {

        return studyProgressService.addProgress(request);
    }

    @GetMapping
    public List<StudyProgressResponse> getAllProgress() {

        return studyProgressService.getAllProgress();
    }

    @GetMapping("/{id}")
    public StudyProgressResponse getProgressById(
            @PathVariable Long id) {

        return studyProgressService.getProgressById(id);
    }

    @PutMapping("/{id}")
    public StudyProgressResponse updateProgress(
            @PathVariable Long id,
            @Valid @RequestBody StudyProgressRequest request) {

        return studyProgressService.updateProgress(
                id,
                request);
    }

    @DeleteMapping("/{id}")
    public String deleteProgress(
            @PathVariable Long id) {

        studyProgressService.deleteProgress(id);

        return "Study progress deleted successfully";
    }
}