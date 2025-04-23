package com.cognizant.project.elearning.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.cognizant.project.elearning.dto.StudentResponseDTO;
import com.cognizant.project.elearning.entity.Role;
import com.cognizant.project.elearning.entity.Student;
import com.cognizant.project.elearning.exception.AllException.StudentDetailNotFound;
import com.cognizant.project.elearning.repository.StudentRepository;
import com.cognizant.project.elearning.service.StudentService;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private StudentResponseDTO studentDTO;
    private Student student;

    @BeforeEach
    public void setUp() {
        studentDTO = new StudentResponseDTO();
        studentDTO.setUserId(1);
        studentDTO.setName("John Doe");
        studentDTO.setPassword("password123");
        studentDTO.setEmail("john.doe@example.com");
        studentDTO.setRole("ROLE_STUDENT");
        studentDTO.setCollege("ABC College");
        studentDTO.setAge(20);

        student = new Student();
        student.setUserId(1);
        student.setName("John Doe");
        student.setPassword("password123");
        student.setEmail("john.doe@example.com");
        student.setRole(Role.ROLE_STUDENT);;
        student.setCollege("ABC College");
        student.setAge(20);
    }

    @Test
    public void testViewStudent_Success() {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(modelMapper.map(student, StudentResponseDTO.class)).thenReturn(studentDTO);

        StudentResponseDTO result = studentService.viewStudent(1);

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
