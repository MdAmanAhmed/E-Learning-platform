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

import com.cognizant.project.elearning_platform.dto.StudentDTO;
import com.cognizant.project.elearning_platform.entity.Role;
import com.cognizant.project.elearning_platform.entity.Student;
import com.cognizant.project.elearning_platform.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning_platform.repository.StudentRepository;
import com.cognizant.project.elearning_platform.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private StudentDTO studentDTO;
    private Student student;

    @BeforeEach
    public void setUp() {
        studentDTO = new StudentDTO();
        studentDTO.setUserId(1);
        studentDTO.setName("John Doe");
        studentDTO.setPassword("password123");
        studentDTO.setEmail("john.doe@example.com");
        studentDTO.setRole(Role.STUDENT); // Assuming Role is properly initialized
        studentDTO.setCollege("ABC College");
        studentDTO.setAge(20);

        student = new Student();
        student.setUserId(1);
        student.setName("John Doe");
        student.setPassword("password123");
        student.setEmail("john.doe@example.com");
        student.setRole(Role.STUDENT); // Assuming Role is properly initialized
        student.setCollege("ABC College");
        student.setAge(20);
    }

    @Test
    public void testAddStudent() {
        when(modelMapper.map(studentDTO, Student.class)).thenReturn(student);
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        when(modelMapper.map(student, StudentDTO.class)).thenReturn(studentDTO);

        StudentDTO result = studentService.addStudent(studentDTO);

        assertEquals(studentDTO, result);
    }

    @Test
    public void testViewStudent_Success() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(modelMapper.map(student, StudentDTO.class)).thenReturn(studentDTO);

        StudentDTO result = studentService.viewStudent(1);

        assertEquals(studentDTO, result);
    }

    @Test
    public void testViewStudent_NotFound() {
        when(studentRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(StudentDetailNotFound.class, () -> {
            studentService.viewStudent(1);
        });
    }
}
