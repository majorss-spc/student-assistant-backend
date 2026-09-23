package com.studentassistant.backend.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.studentassistant.backend.dto.TimetableRequest;
import com.studentassistant.backend.dto.TimetableResponse;
import com.studentassistant.backend.entity.Timetable;
import com.studentassistant.backend.entity.User;
import com.studentassistant.backend.repository.TimetableRepository;
import com.studentassistant.backend.repository.UserRepository;

@Service
public class TimetableService {

    private final TimetableRepository timetableRepository;
    private final UserRepository userRepository;

    public TimetableService(
            TimetableRepository timetableRepository,
            UserRepository userRepository) {

        this.timetableRepository = timetableRepository;
        this.userRepository = userRepository;
    }

    public TimetableResponse addTimetable(
            TimetableRequest request) {

        User user = getCurrentUser();

        Timetable timetable = new Timetable();

        timetable.setDay(request.getDay());
        timetable.setSubject(request.getSubject());
        timetable.setStartTime(request.getStartTime());
        timetable.setEndTime(request.getEndTime());
        timetable.setRoom(request.getRoom());
        timetable.setUser(user);

        Timetable saved =
                timetableRepository.save(timetable);

        return convertToResponse(saved);
    }

    public List<TimetableResponse> getAllTimetable() {

        User user = getCurrentUser();

        return timetableRepository.findByUser(user)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public TimetableResponse getTimetableById(Long id) {

        User user = getCurrentUser();

        Timetable timetable =
                timetableRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Timetable not found"));

        if (!timetable.getUser().getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot access this timetable");
        }

        return convertToResponse(timetable);
    }

    public TimetableResponse updateTimetable(
            Long id,
            TimetableRequest request) {

        User user = getCurrentUser();

        Timetable timetable =
                timetableRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Timetable not found"));

        if (!timetable.getUser().getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot update this timetable");
        }

        timetable.setDay(request.getDay());
        timetable.setSubject(request.getSubject());
        timetable.setStartTime(request.getStartTime());
        timetable.setEndTime(request.getEndTime());
        timetable.setRoom(request.getRoom());

        Timetable updated =
                timetableRepository.save(timetable);

        return convertToResponse(updated);
    }

    public void deleteTimetable(Long id) {

        User user = getCurrentUser();

        Timetable timetable =
                timetableRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Timetable not found"));

        if (!timetable.getUser().getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot delete this timetable");
        }

        timetableRepository.delete(timetable);
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

    private TimetableResponse convertToResponse(
            Timetable timetable) {

        return new TimetableResponse(
                timetable.getId(),
                timetable.getDay(),
                timetable.getSubject(),
                timetable.getStartTime(),
                timetable.getEndTime(),
                timetable.getRoom()
        );
    }
}