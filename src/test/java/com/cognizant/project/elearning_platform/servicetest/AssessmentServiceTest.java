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

import com.cognizant.project.elearning_platform.dto.AssessmentDTO;
import com.cognizant.project.elearning_platform.entity.Assessment;
import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning_platform.repository.AssessmentRepository;
import com.cognizant.project.elearning_platform.repository.CourseRepository;
import com.cognizant.project.elearning_platform.service.AssessmentService;

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

    private AssessmentDTO assessmentDTO;
    private Assessment assessment;
    private Course course;

    @BeforeEach
    public void setUp() {
        assessmentDTO = new AssessmentDTO();
        assessmentDTO.setType("Quiz");
        assessmentDTO.setMaxScore(100);
        course = new Course();
        course.setCourseId(1);
        course.setTitle("Java Programming");
        assessmentDTO.setCourseId(course);

        assessment = new Assessment();
        assessment.setAssessmentId(1);
        assessment.setType("Quiz");
        assessment.setMaxScore(100);
        assessment.setCourseId(course);
    }

    @Test
    public void testCreateAssessment_Success() {
        when(modelMapper.map(assessmentDTO, Assessment.class)).thenReturn(assessment);
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(assessmentRepository.save(any(Assessment.class))).thenReturn(assessment);
        when(modelMapper.map(assessment, AssessmentDTO.class)).thenReturn(assessmentDTO);

        AssessmentDTO result = assessmentService.createAssessment(assessmentDTO, 1);

        assertEquals(assessmentDTO, result);
    }

    @Test
    public void testCreateAssessment_InvalidCourse() {
        when(courseRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InvalidCourse.class, () -> {
            assessmentService.createAssessment(assessmentDTO, 1);
        });
    }
}
