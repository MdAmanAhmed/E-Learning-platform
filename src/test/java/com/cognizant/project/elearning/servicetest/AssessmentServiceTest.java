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

import com.cognizant.project.elearning.dto.AssessmentRequestDTO;
import com.cognizant.project.elearning.dto.AssessmentResponseDTO;
import com.cognizant.project.elearning.entity.Assessment;
import com.cognizant.project.elearning.entity.Course;
import com.cognizant.project.elearning.entity.Instructor;
import com.cognizant.project.elearning.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning.repository.AssessmentRepository;
import com.cognizant.project.elearning.repository.CourseRepository;
import com.cognizant.project.elearning.service.AssessmentService;


@ExtendWith(MockitoExtension.class)
public class AssessmentServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private AssessmentRepository assessmentRepository;

    @InjectMocks
    private AssessmentService assessmentService;

    private AssessmentRequestDTO assessmentRequestDTO;
    private AssessmentResponseDTO assessmentResponseDTO;
    private Course course;
    private Instructor instructor;
    private Assessment assessment;

    @BeforeEach
    public void setUp() {
        assessmentRequestDTO = new AssessmentRequestDTO();
        assessmentRequestDTO.setType("Quiz");
        assessmentRequestDTO.setMaxScore(100);
        course = new Course();
        course.setCourseId(1);
        course.setTitle("Java Programming");
        course.setContentURL("http://example.com/java");
        instructor = new Instructor();
        instructor.setUserId(1);
        instructor.setName("John Doe");
        course.setInstructorId(instructor);
        //assessmentRequestDTO.setCourseId(course);

        assessment = new Assessment();
        assessment.setAssessmentId(1);
        assessment.setType("Quiz");
        assessment.setMaxScore(100);
        assessment.setCourseId(course);

        assessmentResponseDTO = new AssessmentResponseDTO();
        assessmentResponseDTO.setType("Quiz");
        assessmentResponseDTO.setMaxScore(100);
        assessmentResponseDTO.setCourseId(1);
        assessmentResponseDTO.setTitle("Java Programming");
        assessmentResponseDTO.setContentURL("http://example.com/java");
        assessmentResponseDTO.setInstructorId(1);
        assessmentResponseDTO.setInstructorName("John Doe");
    }

    @Test
    public void testCreateAssessment_Success() {
        when(modelMapper.map(assessmentRequestDTO, Assessment.class)).thenReturn(assessment);
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(assessmentRepository.save(any(Assessment.class))).thenReturn(assessment);
        when(modelMapper.map(assessment, AssessmentResponseDTO.class)).thenReturn(assessmentResponseDTO);


        AssessmentResponseDTO result = assessmentService.createAssessment(assessmentRequestDTO, 1);


        assertEquals(assessmentResponseDTO, result);
    }

    @Test
    public void testCreateAssessment_InvalidCourse() {
        when(courseRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InvalidCourse.class, () -> {

            assessmentService.createAssessment(assessmentRequestDTO, 1);

        });
    }
}
