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

import com.studentassistant.backend.dto.TimetableRequest;
import com.studentassistant.backend.dto.TimetableResponse;
import com.studentassistant.backend.service.TimetableService;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {

    private final TimetableService timetableService;

    public TimetableController(
            TimetableService timetableService) {

        this.timetableService = timetableService;
    }

    @PostMapping
    public TimetableResponse addTimetable(
            @Valid @RequestBody TimetableRequest request) {

        return timetableService.addTimetable(request);
    }

    @GetMapping
    public List<TimetableResponse> getAllTimetable() {

        return timetableService.getAllTimetable();
    }

    @GetMapping("/{id}")
    public TimetableResponse getTimetableById(
            @PathVariable Long id) {

        return timetableService.getTimetableById(id);
    }

    @PutMapping("/{id}")
    public TimetableResponse updateTimetable(
            @PathVariable Long id,
            @Valid @RequestBody TimetableRequest request) {

        return timetableService.updateTimetable(
                id,
                request);
    }

    @DeleteMapping("/{id}")
    public String deleteTimetable(
            @PathVariable Long id) {

        timetableService.deleteTimetable(id);

        return "Timetable deleted successfully";
    }
}