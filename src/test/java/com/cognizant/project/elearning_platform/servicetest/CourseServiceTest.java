package com.cognizant.project.elearning_platform.servicetest;


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

import com.cognizant.project.elearning_platform.dto.CourseRequestDTO;
import com.cognizant.project.elearning_platform.entity.Course;
import com.cognizant.project.elearning_platform.entity.Instructor;
import com.cognizant.project.elearning_platform.exception.AllException.InstructorDetailNotFound;
import com.cognizant.project.elearning_platform.exception.AllException.InvalidCourse;
import com.cognizant.project.elearning_platform.repository.CourseRepository;
import com.cognizant.project.elearning_platform.repository.InstructorRepository;
import com.cognizant.project.elearning_platform.service.CourseService;

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

    private CourseRequestDTO courseDTO;
    private Course course;
    private Instructor instructor;

    @BeforeEach
    public void setUp() {
        courseDTO = new CourseRequestDTO();
        courseDTO.setCourseId(1);
        courseDTO.setTitle("Java Programming");
        courseDTO.setDescription("Learn Java from scratch");
        courseDTO.setContentURL("http://example.com/java");
        instructor = new Instructor();
        instructor.setUserId(1);
        instructor.setName("John Doe");
        courseDTO.setInstructorId(instructor);

        course = new Course();
        course.setCourseId(1);
        course.setTitle("Java Programming");
        course.setDescription("Learn Java from scratch");
        course.setContentURL("http://example.com/java");
        course.setInstructorId(instructor);
    }

    @Test
    public void testAddCourse() {
        when(modelMapper.map(courseDTO, Course.class)).thenReturn(course);
        when(instructorRepository.findById(1)).thenReturn(Optional.of(instructor));
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        when(modelMapper.map(course, CourseRequestDTO.class)).thenReturn(courseDTO);

        CourseRequestDTO result = courseService.addCourse(courseDTO, 1);

        assertEquals(courseDTO, result);
    }

    @Test
    public void testAddCourse_InstructorNotFound() {
        when(instructorRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(InstructorDetailNotFound.class, () -> {
            courseService.addCourse(courseDTO, 1);
        });
    }

    @Test
    public void testUpdateCourse_Success() {
        when(courseRepository.findByCourseIdAndInstructorIdUserId(1, 1)).thenReturn(course);
        when(courseRepository.save(any(Course.class))).thenReturn(course);
        when(modelMapper.map(course, CourseRequestDTO.class)).thenReturn(courseDTO);

        CourseRequestDTO result = courseService.updateCourse(1, 1, courseDTO);

        assertEquals(courseDTO, result);
    }

    @Test
    public void testUpdateCourse_InvalidCourse() {
        when(courseRepository.findByCourseIdAndInstructorIdUserId(1, 1)).thenReturn(null);

        assertThrows(InvalidCourse.class, () -> {
            courseService.updateCourse(1, 1, courseDTO);
        });
    }

    @Test
    public void testRemoveCourse_Success() {
        when(courseRepository.findById(1)).thenReturn(Optional.of(course));

        String result = courseService.removeCourse(1);

        verify(courseRepository).delete(course);
        assertEquals("done bro", result);
    }

    @Test
    public void testRemoveCourse_NotFound() {
        when(courseRepository.findById(1)).thenReturn(Optional.empty());

        String result = courseService.removeCourse(1);

        assertEquals("sorrry no course", result);
    }

    @Test
    public void testViewAllCourseByInstructor_Success() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(course);
        when(instructorRepository.findById(1)).thenReturn(Optional.of(instructor));
        when(courseRepository.findByInstructorId(instructor)).thenReturn(courseList);
        when(modelMapper.map(course, CourseRequestDTO.class)).thenReturn(courseDTO);

        List<CourseRequestDTO> result = courseService.viewAllCourse(1);

        assertEquals(1, result.size());
        assertEquals(courseDTO, result.get(0));
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
        when(modelMapper.map(course, CourseRequestDTO.class)).thenReturn(courseDTO);

        List<CourseRequestDTO> result = courseService.viewAllCourse();

        assertEquals(1, result.size());
        assertEquals(courseDTO, result.get(0));
    }
}
