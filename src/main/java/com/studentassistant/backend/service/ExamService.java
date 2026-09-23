package com.studentassistant.backend.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.studentassistant.backend.dto.ExamRequest;
import com.studentassistant.backend.dto.ExamResponse;
import com.studentassistant.backend.entity.Exam;
import com.studentassistant.backend.entity.User;
import com.studentassistant.backend.repository.ExamRepository;
import com.studentassistant.backend.repository.UserRepository;

@Service
public class ExamService {

    private final ExamRepository examRepository;
    private final UserRepository userRepository;

    public ExamService(
            ExamRepository examRepository,
            UserRepository userRepository) {

        this.examRepository = examRepository;
        this.userRepository = userRepository;
    }

    public ExamResponse addExam(ExamRequest request) {

        User user = getCurrentUser();

        Exam exam = new Exam();

        exam.setSubject(request.getSubject());
        exam.setExamDate(request.getExamDate());
        exam.setExamTime(request.getExamTime());
        exam.setRoom(request.getRoom());
        exam.setUser(user);

        Exam saved = examRepository.save(exam);

        return convertToResponse(saved);
    }

    public List<ExamResponse> getAllExams() {

        User user = getCurrentUser();

        return examRepository.findByUser(user)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    public ExamResponse getExamById(Long id) {

        User user = getCurrentUser();

        Exam exam = examRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Exam not found"));

        if (!exam.getUser().getId().equals(user.getId())) {
            throw new RuntimeException(
                    "You cannot access this exam");
        }

        return convertToResponse(exam);
    }

    public ExamResponse updateExam(
            Long id,
            ExamRequest request) {

        User user = getCurrentUser();

        Exam exam = examRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Exam not found"));

        if (!exam.getUser().getId().equals(user.getId())) {
            throw new RuntimeException(
                    "You cannot update this exam");
        }

        exam.setSubject(request.getSubject());
        exam.setExamDate(request.getExamDate());
        exam.setExamTime(request.getExamTime());
        exam.setRoom(request.getRoom());

        Exam updated = examRepository.save(exam);

        return convertToResponse(updated);
    }

    public void deleteExam(Long id) {

        User user = getCurrentUser();

        Exam exam = examRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Exam not found"));

        if (!exam.getUser().getId().equals(user.getId())) {
            throw new RuntimeException(
                    "You cannot delete this exam");
        }

        examRepository.delete(exam);
    }

    private User getCurrentUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    private ExamResponse convertToResponse(Exam exam) {

        return new ExamResponse(
                exam.getId(),
                exam.getSubject(),
                exam.getExamDate(),
                exam.getExamTime(),
                exam.getRoom()
        );
    }
}