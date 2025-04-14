package com.cognizant.project.elearning_platform.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.cognizant.project.elearning_platform.dto.EnrollmentResponseDTO;
import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Enrollment;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.exception.AllException.AlreadyEnrolled;
import com.cognizant.project.elearning_platform.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning_platform.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning_platform.repository.CourseRepository;
import com.cognizant.project.elearning_platform.repository.EnrollmentRepository;
import com.cognizant.project.elearning_platform.repository.StudentRepository;
import com.cognizant.project.elearning_platform.service.EnrollmentService;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private EnrollmentResponseDTO enrollmentDTO;
    private Enrollment enrollment;
    private Student student;
    private Course course;

    @BeforeEach
    public void setUp() {
        enrollmentDTO = new EnrollmentResponseDTO();
        enrollmentDTO.setEnrollmentId(1);
        student = new Student();
        student.setUserId(1);
        student.setName("John Doe");
        course = new Course();
        course.setCourseId(1);
        course.setTitle("Java Programming");
        enrollmentDTO.setStudentId(student);
        enrollmentDTO.setCourseId(course);
        enrollmentDTO.setProgress(0);

        enrollment = new Enrollment();
        enrollment.setEnrollmentId(1);
        enrollment.setStudentId(student);
        enrollment.setCourseId(course);
        enrollment.setProgress(0);
    }

    @Test
    public void testEnroll_Success() {
        when(modelMapper.map(enrollmentDTO, Enrollment.class)).thenReturn(enrollment);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(enrollmentRepository.findByStudentIdAndCourseId(student, course)).thenReturn(null);
        when(enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);
        when(modelMapper.map(enrollment, EnrollmentResponseDTO.class)).thenReturn(enrollmentDTO);

        EnrollmentResponseDTO result = enrollmentService.enroll(1, 1, enrollmentDTO);

        assertEquals(enrollmentDTO, result);
    }

    @Test
    public void testEnroll_StudentNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentDetailNotFound.class, () -> {
            enrollmentService.enroll(1, 1, enrollmentDTO);
        });
    }

    @Test
    public void testEnroll_InvalidCourse() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InvalidCourse.class, () -> {
            enrollmentService.enroll(1, 1, enrollmentDTO);
        });
    }

    @Test
    public void testEnroll_AlreadyEnrolled() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(enrollmentRepository.findByStudentIdAndCourseId(student, course)).thenReturn(enrollment);

        assertThrows(AlreadyEnrolled.class, () -> {
            enrollmentService.enroll(1, 1, enrollmentDTO);
        });
    }

    @Test
    public void testViewEnrolled_Success() {
        List<Enrollment> enrollList = new ArrayList<>();
        enrollList.add(enrollment);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(enrollmentRepository.findByStudentId(student)).thenReturn(enrollList);

        List<Course> result = enrollmentService.viewEnrolled(1);

        assertEquals(1, result.size());
        assertEquals(course, result.get(0));
    }

    @Test
    public void testViewEnrolled_StudentNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentDetailNotFound.class, () -> {
            enrollmentService.viewEnrolled(1);
        });
    }
}
