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

import com.studentassistant.backend.dto.ReminderRequest;
import com.studentassistant.backend.dto.ReminderResponse;
import com.studentassistant.backend.service.ReminderService;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    // Reminder business logic
    private final ReminderService reminderService;

    // Constructor injection
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    // CREATE reminder
    @PostMapping
    public ReminderResponse addReminder(
            @Valid @RequestBody ReminderRequest request) {

        return reminderService.addReminder(request);
    }

    // GET all reminders
    @GetMapping
    public List<ReminderResponse> getAllReminders() {

        return reminderService.getAllReminders();
    }

    // GET single reminder
    @GetMapping("/{id}")
    public ReminderResponse getReminderById(
            @PathVariable Long id) {

        return reminderService.getReminderById(id);
    }

    // UPDATE reminder
    @PutMapping("/{id}")
    public ReminderResponse updateReminder(
            @PathVariable Long id,
            @Valid @RequestBody ReminderRequest request) {

        return reminderService.updateReminder(id, request);
    }

    // DELETE reminder
    @DeleteMapping("/{id}")
    public String deleteReminder(
            @PathVariable Long id) {

        reminderService.deleteReminder(id);

        return "Reminder deleted successfully";
    }
}