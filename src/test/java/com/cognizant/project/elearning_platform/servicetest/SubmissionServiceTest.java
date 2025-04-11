package com.cognizant.project.elearning_platform.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.cognizant.project.elearning_platform.dto.SubmissionDTO;
import com.cognizant.project.elearning_platform.entity.Assessment;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.entity.Submission;
import com.cognizant.project.elearning_platform.exception.AllException.AssessmentNotFound;
import com.cognizant.project.elearning_platform.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning_platform.repository.AssessmentRepository;
import com.cognizant.project.elearning_platform.repository.StudentRepository;
import com.cognizant.project.elearning_platform.repository.SubmissionRepository;
import com.cognizant.project.elearning_platform.service.SubmissionService;

@ExtendWith(MockitoExtension.class)
public class SubmissionServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private SubmissionRepository submissionRepository;

    @Mock
    private AssessmentRepository assessmentRepository;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private SubmissionService submissionService;

    private SubmissionDTO submissionDTO;
    private Submission submission;
    private Student student;
    private Assessment assessment;

    @BeforeEach
    public void setUp() {
        submissionDTO = new SubmissionDTO();
        submissionDTO.setSubmissionId(1);
        student = new Student();
        student.setUserId(1);
        student.setName("John Doe");
        assessment = new Assessment();
        assessment.setAssessmentId(1);
        assessment.setType("Quiz");
        submissionDTO.setStudentId(student);
        submissionDTO.setAssessmentId(assessment);
        submissionDTO.setScore(85);

        submission = new Submission();
        submission.setSubmissionId(1);
        submission.setStudentId(student);
        submission.setAssessmentId(assessment);
        submission.setScore(85);
    }

    @Test
    public void testSubmitAssessment_Success() {
        when(modelMapper.map(submissionDTO, Submission.class)).thenReturn(submission);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(assessmentRepository.findById(1)).thenReturn(Optional.of(assessment));
        when(submissionRepository.save(any(Submission.class))).thenReturn(submission);
        when(modelMapper.map(submission, SubmissionDTO.class)).thenReturn(submissionDTO);

        SubmissionDTO result = submissionService.submitAssessment(submissionDTO, 1, 1);

        assertEquals(submissionDTO, result);
    }

    @Test
    public void testSubmitAssessment_StudentNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentDetailNotFound.class, () -> {
            submissionService.submitAssessment(submissionDTO, 1, 1);
        });
    }

    @Test
    public void testSubmitAssessment_AssessmentNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(assessmentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(AssessmentNotFound.class, () -> {
            submissionService.submitAssessment(submissionDTO, 1, 1);
        });
    }
}
