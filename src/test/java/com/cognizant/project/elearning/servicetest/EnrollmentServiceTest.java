package com.cognizant.project.elearning.servicetest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.cognizant.project.elearning.dto.CourseResponseDTO;
import com.cognizant.project.elearning.dto.EnrollmentResponseDTO;
import com.cognizant.project.elearning.entity.Course;
import com.cognizant.project.elearning.entity.Enrollment;
import com.cognizant.project.elearning.entity.Student;
import com.cognizant.project.elearning.exception.AllException.AlreadyEnrolled;
import com.cognizant.project.elearning.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning.repository.CourseRepository;
import com.cognizant.project.elearning.repository.EnrollmentRepository;
import com.cognizant.project.elearning.repository.StudentRepository;
import com.cognizant.project.elearning.service.EnrollmentService;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    private EnrollmentResponseDTO enrollmentResponseDTO;
    private CourseResponseDTO courseResponseDTO;
    private Enrollment enrollment;
    private Student student;
    private Course course;

    @BeforeEach
    public void setUp() {
        student = new Student();
        student.setUserId(1);
        student.setName("Jane Doe");

        course = new Course();
        course.setCourseId(1);
        course.setTitle("Java Programming");
        course.setDescription("Learn Java from scratch");
        course.setContentURL("http://example.com/java");

        enrollment = new Enrollment();
        enrollment.setEnrollmentId(1);
        enrollment.setStudentId(student);
        enrollment.setCourseId(course);

        enrollmentResponseDTO = new EnrollmentResponseDTO();
        enrollmentResponseDTO.setEnrollmentId(1);
        enrollmentResponseDTO.setStudentId(1);
        enrollmentResponseDTO.setCourseId(1);
        enrollmentResponseDTO.setCourseTitle("Java Programming");
        enrollmentResponseDTO.setInstructorName("John Doe");

        courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setCourseId(1);
        courseResponseDTO.setTitle("Java Programming");
        courseResponseDTO.setDescription("Learn Java from scratch");
        courseResponseDTO.setContentURL("http://example.com/java");
        courseResponseDTO.setInstructorId(1);
        courseResponseDTO.setInstructorName("John Doe");
    }


    @Test
    public void testEnroll_StudentNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentDetailNotFound.class, () -> {
            enrollmentService.enroll(1, 1);
        });
    }

    @Test
    public void testEnroll_CourseNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InvalidCourse.class, () -> {
            enrollmentService.enroll(1, 1);
        });
    }

    @Test
    public void testEnroll_AlreadyEnrolled() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));
        when(enrollmentRepository.findByStudentIdAndCourseId(student, course)).thenReturn(enrollment);

        assertThrows(AlreadyEnrolled.class, () -> {
            enrollmentService.enroll(1, 1);
        });
    }


    @Test
    public void testViewEnrolled_StudentNotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentDetailNotFound.class, () -> {
            enrollmentService.viewEnrolled(1);
        });
    }
}
