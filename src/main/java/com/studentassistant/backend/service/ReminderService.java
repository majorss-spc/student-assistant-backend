package com.studentassistant.backend.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.studentassistant.backend.dto.ReminderRequest;
import com.studentassistant.backend.dto.ReminderResponse;
import com.studentassistant.backend.entity.Reminder;
import com.studentassistant.backend.entity.User;
import com.studentassistant.backend.repository.ReminderRepository;
import com.studentassistant.backend.repository.UserRepository;

@Service
public class ReminderService {

    // Reminder database repository
    private final ReminderRepository reminderRepository;

    // User database repository
    private final UserRepository userRepository;

    // Constructor injection
    public ReminderService(
            ReminderRepository reminderRepository,
            UserRepository userRepository) {

        this.reminderRepository = reminderRepository;
        this.userRepository = userRepository;
    }

    // ---------------------------------------------------------
    // ADD REMINDER
    // ---------------------------------------------------------
    public ReminderResponse addReminder(ReminderRequest request) {

        // Current logged-in user nikalo
        User user = getCurrentUser();

        // New reminder object
        Reminder reminder = new Reminder();

        // Request se data entity me set karo
        reminder.setTitle(request.getTitle());
        reminder.setReminderDate(request.getReminderDate());
        reminder.setReminderTime(request.getReminderTime());
        reminder.setDescription(request.getDescription());
        reminder.setCompleted(request.isCompleted());

        // Reminder ko current user se connect karo
        reminder.setUser(user);

        // Database me save
        Reminder savedReminder =
                reminderRepository.save(reminder);

        // Response return
        return convertToResponse(savedReminder);
    }

    // ---------------------------------------------------------
    // GET ALL REMINDERS
    // ---------------------------------------------------------
    public List<ReminderResponse> getAllReminders() {

        // Current user
        User user = getCurrentUser();

        // Sirf current user ke reminders
        return reminderRepository.findByUser(user)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // ---------------------------------------------------------
    // GET REMINDER BY ID
    // ---------------------------------------------------------
    public ReminderResponse getReminderById(Long id) {

        // Current user
        User user = getCurrentUser();

        // Reminder find karo
        Reminder reminder =
                reminderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Reminder not found"));

        // Security check
        if (!reminder.getUser().getId().equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot access this reminder");
        }

        // Response
        return convertToResponse(reminder);
    }

    // ---------------------------------------------------------
    // UPDATE REMINDER
    // ---------------------------------------------------------
    public ReminderResponse updateReminder(
            Long id,
            ReminderRequest request) {

        // Current user
        User user = getCurrentUser();

        // Existing reminder find karo
        Reminder reminder =
                reminderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Reminder not found"));

        // Security check
        if (!reminder.getUser().getId().equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot update this reminder");
        }

        // New values set karo
        reminder.setTitle(request.getTitle());
        reminder.setReminderDate(request.getReminderDate());
        reminder.setReminderTime(request.getReminderTime());
        reminder.setDescription(request.getDescription());
        reminder.setCompleted(request.isCompleted());

        // Database update
        Reminder savedReminder =
                reminderRepository.save(reminder);

        // Updated response
        return convertToResponse(savedReminder);
    }

    // ---------------------------------------------------------
    // DELETE REMINDER
    // ---------------------------------------------------------
    public void deleteReminder(Long id) {

        // Current user
        User user = getCurrentUser();

        // Reminder find
        Reminder reminder =
                reminderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Reminder not found"));

        // Security check
        if (!reminder.getUser().getId().equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot delete this reminder");
        }

        // Delete
        reminderRepository.delete(reminder);
    }

    // ---------------------------------------------------------
    // CURRENT USER
    // ---------------------------------------------------------
    private User getCurrentUser() {

        // JWT authentication se email nikalo
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        // Email se user find karo
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));
    }

    // ---------------------------------------------------------
    // ENTITY -> RESPONSE
    // ---------------------------------------------------------
    private ReminderResponse convertToResponse(
            Reminder reminder) {

        // Flutter ke expected response format me convert
        return new ReminderResponse(
                reminder.getId(),
                reminder.getTitle(),
                reminder.getReminderDate(),
                reminder.getReminderTime(),
                reminder.getDescription(),
                reminder.isCompleted()
        );
    }
}