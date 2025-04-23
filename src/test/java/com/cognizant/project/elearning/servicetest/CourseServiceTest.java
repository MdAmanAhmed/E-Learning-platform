package com.cognizant.project.elearning.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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

import com.cognizant.project.elearning.dto.CourseRequestDTO;
import com.cognizant.project.elearning.dto.CourseResponseDTO;
import com.cognizant.project.elearning.entity.Course;
import com.cognizant.project.elearning.entity.Instructor;
import com.cognizant.project.elearning.exception.AllException.InstructorDetailNotFound;
import com.cognizant.project.elearning.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning.repository.CourseRepository;
import com.cognizant.project.elearning.repository.InstructorRepository;
import com.cognizant.project.elearning.service.CourseService;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private CourseService courseService;

    private CourseRequestDTO courseRequestDTO;
    private CourseResponseDTO courseResponseDTO;
    private Course course;
    private Instructor instructor;

    @BeforeEach
    public void setUp() {
        courseRequestDTO = new CourseRequestDTO();
        courseRequestDTO.setTitle("Java Programming");
        courseRequestDTO.setDescription("Learn Java from scratch");
        courseRequestDTO.setContentURL("http://example.com/java");

        instructor = new Instructor();
        instructor.setUserId(1);
        instructor.setName("John Doe");

        course = new Course();
        course.setCourseId(1);
        course.setTitle("Java Programming");
        course.setDescription("Learn Java from scratch");
        course.setContentURL("http://example.com/java");
        course.setInstructorId(instructor);

        courseResponseDTO = new CourseResponseDTO();
        courseResponseDTO.setCourseId(1);
        courseResponseDTO.setTitle("Java Programming");
        courseResponseDTO.setDescription("Learn Java from scratch");
        courseResponseDTO.setContentURL("http://example.com/java");
        courseResponseDTO.setInstructorId(1);
        courseResponseDTO.setInstructorName("John Doe");
    }

    @Test
    public void testAddCourse_Success() {
        when(modelMapper.map(courseRequestDTO, Course.class)).thenReturn(course);
        when(instructorRepository.findById(1)).thenReturn(Optional.of(instructor));
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        when(modelMapper.map(course, CourseResponseDTO.class)).thenReturn(courseResponseDTO);

        CourseResponseDTO result = courseService.addCourse(courseRequestDTO, 1);

        assertEquals(courseResponseDTO, result);
    }

    @Test
    public void testAddCourse_InstructorNotFound() {
        when(instructorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InstructorDetailNotFound.class, () -> {
            courseService.addCourse(courseRequestDTO, 1);
        });
    }


    @Test
    public void testUpdateCourse_InvalidCourse() {
        when(courseRepository.findByCourseIdAndInstructorIdUserId(1, 1)).thenReturn(null);

        assertThrows(InvalidCourse.class, () -> {
            courseService.updateCourse(1, 1, courseRequestDTO);
        });
    }

    @Test
    public void testRemoveCourse_Success() {
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        String result = courseService.removeCourse(1, 1);

        verify(courseRepository).delete(course);
        assertEquals("course removed", result);
    }

    @Test
    public void testRemoveCourse_NotFound() {
        when(courseRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InvalidCourse.class, () -> {
            courseService.removeCourse(1, 1);
        });
    }


    @Test
    public void testViewAllCourseByInstructor_Success() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(instructorRepository.findById(1)).thenReturn(Optional.of(instructor));
        when(courseRepository.findByInstructorId(instructor)).thenReturn(courseList);
        when(modelMapper.map(course, CourseRequestDTO.class)).thenReturn(courseRequestDTO);

        List<CourseRequestDTO> result = courseService.viewAllCourse(1);

        assertEquals(1, result.size());
        assertEquals(courseRequestDTO, result.get(0));
    }

    @Test
    public void testViewAllCourseByInstructor_InstructorNotFound() {
        when(instructorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InstructorDetailNotFound.class, () -> {
            courseService.viewAllCourse(1);
        });
    }

    @Test
    public void testViewAllCourse() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(courseRepository.findAll()).thenReturn(courseList);
        when(modelMapper.map(course, CourseRequestDTO.class)).thenReturn(courseRequestDTO);

        List<CourseRequestDTO> result = courseService.viewAllCourse();

        assertEquals(1, result.size());
        assertEquals(courseRequestDTO, result.get(0));
    }
}
