package com.cognizant.project.elearning.servicetest;

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

import com.cognizant.project.elearning.dto.SubmissionResponseDTO;
import com.cognizant.project.elearning.entity.Assessment;
import com.cognizant.project.elearning.entity.Student;
import com.cognizant.project.elearning.entity.Submission;
import com.cognizant.project.elearning.exception.AllException.AssessmentNotFound;
import com.cognizant.project.elearning.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning.repository.AssessmentRepository;
import com.cognizant.project.elearning.repository.StudentRepository;
import com.cognizant.project.elearning.repository.SubmissionRepository;
import com.cognizant.project.elearning.service.SubmissionService;

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

    private SubmissionResponseDTO submissionResponseDTO;
    private Submission submission;
    private Student student;
    private Assessment assessment;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setUserId(1);
        student.setName("John Doe");

        assessment = new Assessment();
        assessment.setAssessmentId(1);
        assessment.setType("Quiz");

        submission = new Submission();
        submission.setSubmissionId(1);
        submission.setStudentId(student);
        submission.setAssessmentId(assessment);

        submissionResponseDTO = new SubmissionResponseDTO();
        submissionResponseDTO.setSubmissionId(1);
        submissionResponseDTO.setAssessmentId(1);
        submissionResponseDTO.setStudentId(1);
        submissionResponseDTO.setType("Quiz");
        submissionResponseDTO.setMaxScore(100);
        submissionResponseDTO.setCourseId(1);
        submissionResponseDTO.setTitle("Java Programming");
        submissionResponseDTO.setInstructorName("John Doe");
    }

//    @Test
//    public void testSubmitAssessment_Success() {
//        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
//        when(assessmentRepository.findById(1)).thenReturn(Optional.of(assessment));
//        when(submissionRepository.save(any(Submission.class))).thenReturn(submission);
//        when(modelMapper.map(submission, SubmissionResponseDTO.class)).thenReturn(submissionResponseDTO);
//
//        SubmissionResponseDTO result = submissionService.submitAssessment(1, 1);
//
//        assertEquals(submissionResponseDTO, result);
//    }

    @Test
    public void testSubmitAssessment_StudentNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentDetailNotFound.class, () -> {
            submissionService.submitAssessment(1, 1);
        });
    }

    @Test
    public void testSubmitAssessment_AssessmentNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(assessmentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(AssessmentNotFound.class, () -> {
            submissionService.submitAssessment(1, 1);
        });
    }
}
