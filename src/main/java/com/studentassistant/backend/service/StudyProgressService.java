package com.studentassistant.backend.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.studentassistant.backend.dto.StudyProgressRequest;
import com.studentassistant.backend.dto.StudyProgressResponse;
import com.studentassistant.backend.entity.StudyProgress;
import com.studentassistant.backend.entity.User;
import com.studentassistant.backend.repository.StudyProgressRepository;
import com.studentassistant.backend.repository.UserRepository;

@Service
public class StudyProgressService {

    private final StudyProgressRepository studyProgressRepository;
    private final UserRepository userRepository;

    public StudyProgressService(
            StudyProgressRepository studyProgressRepository,
            UserRepository userRepository) {

        this.studyProgressRepository = studyProgressRepository;
        this.userRepository = userRepository;
    }

    public StudyProgressResponse addProgress(
            StudyProgressRequest request) {

        User user = getCurrentUser();

        if (request.getCompletedTopics()
                > request.getTotalTopics()) {

            throw new RuntimeException(
                    "Completed topics cannot be greater than total topics");
        }

        StudyProgress progress = new StudyProgress();

        progress.setSubject(request.getSubject());
        progress.setTotalTopics(request.getTotalTopics());
        progress.setCompletedTopics(request.getCompletedTopics());
        progress.setUser(user);

        StudyProgress savedProgress =
                studyProgressRepository.save(progress);

        return convertToResponse(savedProgress);
    }

    public List<StudyProgressResponse> getAllProgress() {

        User user = getCurrentUser();

        return studyProgressRepository.findByUser(user)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public StudyProgressResponse getProgressById(Long id) {

        User user = getCurrentUser();

        StudyProgress progress =
                studyProgressRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Study progress not found"));

        if (!progress.getUser().getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot access this study progress");
        }

        return convertToResponse(progress);
    }

    public StudyProgressResponse updateProgress(
            Long id,
            StudyProgressRequest request) {

        User user = getCurrentUser();

        StudyProgress progress =
                studyProgressRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Study progress not found"));

        if (!progress.getUser().getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot update this study progress");
        }

        if (request.getCompletedTopics()
                > request.getTotalTopics()) {

            throw new RuntimeException(
                    "Completed topics cannot be greater than total topics");
        }

        progress.setSubject(request.getSubject());
        progress.setTotalTopics(request.getTotalTopics());
        progress.setCompletedTopics(
                request.getCompletedTopics());

        StudyProgress savedProgress =
                studyProgressRepository.save(progress);

        return convertToResponse(savedProgress);
    }

    public void deleteProgress(Long id) {

        User user = getCurrentUser();

        StudyProgress progress =
                studyProgressRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Study progress not found"));

        if (!progress.getUser().getId()
                .equals(user.getId())) {

            throw new RuntimeException(
                    "You cannot delete this study progress");
        }

        studyProgressRepository.delete(progress);
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

    private StudyProgressResponse convertToResponse(
            StudyProgress progress) {

        double percentage =
                ((double) progress.getCompletedTopics()
                        / progress.getTotalTopics())
                        * 100;

        return new StudyProgressResponse(
                progress.getId(),
                progress.getSubject(),
                progress.getTotalTopics(),
                progress.getCompletedTopics(),
                percentage
        );
    }
}